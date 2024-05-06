package com.lasagnerd.odin.insights.typeInference;

import com.intellij.psi.PsiNamedElement;
import com.lasagnerd.odin.insights.OdinInsightUtils;
import com.lasagnerd.odin.insights.OdinScope;
import com.lasagnerd.odin.insights.typeSystem.*;
import com.lasagnerd.odin.lang.OdinLangSyntaxAnnotator;
import com.lasagnerd.odin.lang.psi.*;
import org.jetbrains.annotations.NotNull;

public class OdinInferenceEngine extends OdinVisitor {

    final OdinScope scope;

    TsOdinType type;
    OdinImportDeclarationStatement importDeclarationStatement;

    boolean isImport;

    public OdinInferenceEngine(OdinScope scope) {
        this.scope = scope;
    }

    public static OdinTypeInferenceResult inferType(OdinScope scope, OdinExpression expression) {
        OdinInferenceEngine odinExpressionTypeResolver = new OdinInferenceEngine(scope);
        expression.accept(odinExpressionTypeResolver);
        OdinTypeInferenceResult typeInferenceResult = new OdinTypeInferenceResult();
        typeInferenceResult.setImportDeclarationStatement(odinExpressionTypeResolver.importDeclarationStatement);
        typeInferenceResult.setType(odinExpressionTypeResolver.type);
        typeInferenceResult.setImport(odinExpressionTypeResolver.isImport);
        return typeInferenceResult;
    }

    public static TsOdinType doInferType(OdinScope scope, @NotNull OdinExpression expression) {
        OdinInferenceEngine odinExpressionTypeResolver = new OdinInferenceEngine(scope);
        expression.accept(odinExpressionTypeResolver);

        TsOdinType type = odinExpressionTypeResolver.type;
        if(type == null) {
            return TsOdinType.UNKNOWN;
        }
        return type;
    }

    @Override
    public void visitTypeDefinitionExpression(@NotNull OdinTypeDefinitionExpression o) {
        TsOdinBuiltInType tsOdinBuiltInType = new TsOdinBuiltInType();
        tsOdinBuiltInType.setName("typeid");
        this.type = tsOdinBuiltInType;
    }

    @Override
    public void visitRefExpression(@NotNull OdinRefExpression refExpression) {
        OdinScope localScope;
        if (refExpression.getExpression() != null) {
            // solve for expression first. This defines the scope
            // extract scope

            TsOdinType tsOdinType = doInferType(scope, refExpression.getExpression());
            localScope = OdinInsightUtils.getScopeProvidedByType(tsOdinType);

            // The resolved polymorphic types must be taken over from type scope
            this.scope.addTypes(localScope);
        } else {
            localScope = this.scope;
        }

        // using current scope, find identifier declaration and extract type
        String name = refExpression.getIdentifier().getText();
        PsiNamedElement namedElement = localScope.getNamedElement(name);
        if (namedElement instanceof OdinImportDeclarationStatement) {
            isImport = true;
            importDeclarationStatement = (OdinImportDeclarationStatement) namedElement;
        } else if (namedElement instanceof OdinDeclaredIdentifier declaredIdentifier) {
            OdinDeclaration odinDeclaration = OdinInsightUtils.findFirstParentOfType(declaredIdentifier, true, OdinDeclaration.class);

            if (odinDeclaration instanceof OdinImportDeclarationStatement) {
                isImport = true;
                importDeclarationStatement = (OdinImportDeclarationStatement) odinDeclaration;
            } else {
                this.type = resolveTypeOfDeclaration(this.scope, declaredIdentifier, odinDeclaration);
            }
        } else if (namedElement == null) {
            if (OdinLangSyntaxAnnotator.RESERVED_TYPES.contains(name)) {
                TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.BUILTIN);
                tsOdinMetaType.setName(name);
                this.type = tsOdinMetaType;
            }
        }
    }

    @Override
    public void visitCompoundLiteralExpression(@NotNull OdinCompoundLiteralExpression o) {
        if (o.getCompoundLiteral() instanceof OdinCompoundLiteralTyped typed) {
            OdinType typeExpression = typed.getType();
            this.type = OdinTypeResolver.resolveType(this.scope, typeExpression);
        }
    }

    @Override
    public void visitCallExpression(@NotNull OdinCallExpression o) {
        // Get type of expression. If it is callable, retrieve the return type and set that as result
        TsOdinType tsOdinType = doInferType(scope, o.getExpression());
        if (tsOdinType instanceof TsOdinMetaType tsOdinMetaType) {
            if (tsOdinMetaType.getMetaType() == TsOdinMetaType.MetaType.PROCEDURE) {
                TsOdinProcedureType procedureType = (TsOdinProcedureType) OdinTypeResolver.resolveMetaType(scope, tsOdinMetaType);
                if (procedureType != null && !procedureType.getReturnTypes().isEmpty()) {
                    TsOdinProcedureType instantiatedType = OdinTypeInstantiator
                            .instantiateProcedure(scope, o.getArgumentList(), procedureType);
                    this.type = instantiatedType.getReturnTypes().get(0);
                }
            }

            if(tsOdinMetaType.getMetaType() == TsOdinMetaType.MetaType.STRUCT) {
                TsOdinStructType structType = (TsOdinStructType) OdinTypeResolver.resolveMetaType(scope, tsOdinMetaType);
                if (structType != null) {
                    this.type = OdinTypeInstantiator.instantiateStruct(scope, o.getArgumentList(), structType);
                }
            }
        }
    }

    @Override
    public void visitIndexExpression(@NotNull OdinIndexExpression o) {
        // get type of expression. IF it is indexable (array, matrix, bitset, map), retrieve the indexed type and set that as result
        OdinExpression expression = o.getExpression();
        TsOdinType tsOdinType = doInferType(scope, expression);

        if (tsOdinType instanceof TsOdinPointerType pointerType) {
            tsOdinType = pointerType.getDereferencedType();
        }

        if (tsOdinType instanceof TsOdinArrayType arrayType) {
            this.type = arrayType.getElementType();
        }

        if (tsOdinType instanceof TsOdinMapType mapType) {
            this.type = mapType.getValueType();
        }
    }

    @Override
    public void visitDereferenceExpression(@NotNull OdinDereferenceExpression o) {
        // get type of expression. If it is a pointer, retrieve the dereferenced type and set that as result
        OdinExpression expression = o.getExpression();
        TsOdinType tsOdinType = doInferType(scope, expression);
        if (tsOdinType instanceof TsOdinPointerType pointerType) {
            this.type = pointerType.getDereferencedType();
        }
    }

    @Override
    public void visitParenthesizedExpression(@NotNull OdinParenthesizedExpression o) {
        OdinExpression expression = o.getExpression();
        if (expression != null) {
            this.type = doInferType(scope, expression);
        }
    }

    @Override
    public void visitProcedureExpression(@NotNull OdinProcedureExpression o) {
        // get type of expression. If it is a procedure, retrieve the return type and set that as result
        var procedureType = o.getProcedureTypeContainer().getProcedureType();
        TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.PROCEDURE);
        tsOdinMetaType.setType(procedureType);

        this.type = tsOdinMetaType;
    }

    @Override
    public void visitCastExpression(@NotNull OdinCastExpression o) {
        OdinTypeDefinitionExpression typeDefinitionExpression = (OdinTypeDefinitionExpression) o.getTypeDefinitionExpression();
        this.type = OdinTypeResolver.resolveType(scope, typeDefinitionExpression.getType());
    }

    @Override
    public void visitOrElseExpression(@NotNull OdinOrElseExpression o) {

    }

    public static TsOdinType resolveTypeOfDeclaration(OdinScope parentScope,
                                                      OdinDeclaredIdentifier declaredIdentifier,
                                                      OdinDeclaration odinDeclaration) {
        if (odinDeclaration instanceof OdinVariableDeclarationStatement declarationStatement) {
            var mainType = declarationStatement.getTypeDefinitionExpression().getType();
            //return getDeclaredIdentifierQualifiedType(parentScope, mainType);
            return OdinTypeResolver.resolveType(parentScope, mainType);
        }

        if (odinDeclaration instanceof OdinVariableInitializationStatement initializationStatement) {
            if (initializationStatement.getTypeDefinitionExpression() != null) {
                OdinType mainTypeExpression = initializationStatement.getTypeDefinitionExpression().getType();
                return OdinTypeResolver.resolveType(parentScope, mainTypeExpression);
            }

            int index = initializationStatement.getIdentifierList().getDeclaredIdentifierList().indexOf(declaredIdentifier);
            OdinExpression odinExpression = initializationStatement.getExpressionsList().getExpressionList().get(index);
            return doInferType(parentScope, odinExpression);
        }

        if (odinDeclaration instanceof OdinConstantInitializationStatement initializationStatement) {
            if (initializationStatement.getTypeDefinitionExpression() != null) {
                OdinType mainType = initializationStatement.getTypeDefinitionExpression().getType();
                return OdinTypeResolver.resolveType(parentScope, mainType);
            }
            int index = initializationStatement.getIdentifierList().getDeclaredIdentifierList().indexOf(declaredIdentifier);
            OdinExpression odinExpression = initializationStatement.getExpressionsList().getExpressionList().get(index);
            return doInferType(parentScope, odinExpression);
        }

        if (odinDeclaration instanceof OdinFieldDeclarationStatement fieldDeclarationStatement) {
            OdinType type;
            if (fieldDeclarationStatement.getTypeDefinitionExpression() instanceof OdinType expr) {
                type = expr;
            } else {

                OdinTypeDefinitionExpression typeDefinition = fieldDeclarationStatement.getTypeDefinitionExpression();
                type = typeDefinition.getType();
            }

            return OdinTypeResolver.resolveType(parentScope, type);
        }

        if (odinDeclaration instanceof OdinParameterDecl parameterDeclaration) {
            return OdinTypeResolver.resolveType(parentScope, parameterDeclaration.getTypeDefinition().getType());
        }

        if (odinDeclaration instanceof OdinParameterInitialization parameterInitialization) {
            OdinTypeDefinitionExpression typeDefinition = parameterInitialization.getTypeDefinition();
            if (typeDefinition != null) {
                return OdinTypeResolver.resolveType(parentScope, typeDefinition.getType());
            }

            OdinExpression odinExpression = parameterInitialization.getExpression();
            return doInferType(parentScope, odinExpression);
        }

        // Meta types
        if (odinDeclaration instanceof OdinProcedureDeclarationStatement procedure) {
            TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.PROCEDURE);
            tsOdinMetaType.setDeclaration(procedure);
            tsOdinMetaType.setType(procedure.getProcedureType());
            tsOdinMetaType.setDeclaredIdentifier(declaredIdentifier);
            tsOdinMetaType.setName(declaredIdentifier.getName());
            return tsOdinMetaType;
        }

        if (odinDeclaration instanceof OdinStructDeclarationStatement structDeclarationStatement) {
            TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.STRUCT);
            tsOdinMetaType.setDeclaration(structDeclarationStatement);
            tsOdinMetaType.setType(structDeclarationStatement.getStructType());
            tsOdinMetaType.setDeclaredIdentifier(declaredIdentifier);
            tsOdinMetaType.setName(declaredIdentifier.getName());
            return tsOdinMetaType;
        }

        if (odinDeclaration instanceof OdinEnumDeclarationStatement enumDeclarationStatement) {
            TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.ENUM);
            tsOdinMetaType.setDeclaration(enumDeclarationStatement);
            tsOdinMetaType.setType(enumDeclarationStatement.getEnumType());
            tsOdinMetaType.setDeclaredIdentifier(declaredIdentifier);
            tsOdinMetaType.setName(declaredIdentifier.getName());
            return tsOdinMetaType;
        }

        if (odinDeclaration instanceof OdinUnionDeclarationStatement unionDeclarationStatement) {
            TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.UNION);
            tsOdinMetaType.setDeclaration(unionDeclarationStatement);
            tsOdinMetaType.setType(unionDeclarationStatement.getUnionType());
            tsOdinMetaType.setDeclaredIdentifier(declaredIdentifier);
            tsOdinMetaType.setName(declaredIdentifier.getName());
            return tsOdinMetaType;
        }


        if(odinDeclaration instanceof OdinPolymorphicType polymorphicType) {
            TsOdinMetaType tsOdinMetaType = new TsOdinMetaType(TsOdinMetaType.MetaType.POLYMORPHIC_PARAMETER);
            tsOdinMetaType.setDeclaration(polymorphicType);
            tsOdinMetaType.setType(polymorphicType);
            tsOdinMetaType.setDeclaredIdentifier(declaredIdentifier);
            tsOdinMetaType.setName(declaredIdentifier.getName());
            return tsOdinMetaType;
        }


        return TsOdinType.UNKNOWN;
    }
}