package com.lasagnerd.odin.codeInsight.symbols;

import com.intellij.psi.util.PsiTreeUtil;
import com.lasagnerd.odin.codeInsight.OdinInsightUtils;
import com.lasagnerd.odin.codeInsight.typeInference.OdinInferenceEngine;
import com.lasagnerd.odin.codeInsight.typeSystem.TsOdinArrayType;
import com.lasagnerd.odin.codeInsight.typeSystem.TsOdinType;
import com.lasagnerd.odin.lang.psi.*;

import static com.lasagnerd.odin.codeInsight.OdinInsightUtils.getSwizzleFields;
import static com.lasagnerd.odin.codeInsight.imports.OdinImportUtils.getSymbolsOfImportedPackage;

public class OdinReferenceResolver {
    public static OdinSymbolTable resolve(OdinSymbolTable symbolTable, OdinExpression valueExpression) {
        // Add filter for referenceable elements
        TsOdinType type = OdinInferenceEngine.inferType(symbolTable, valueExpression);
        return OdinInsightUtils.getTypeElements(type, true);
    }

    public static OdinSymbolTable resolve(OdinSymbolTable symbolTable, OdinType type) {
        OdinQualifiedType qualifiedType = PsiTreeUtil.getParentOfType(type, false, OdinQualifiedType.class);
        if (qualifiedType != null) {
            return resolve(symbolTable, qualifiedType);
        }
        return OdinSymbolTable.EMPTY;
    }

    public static OdinSymbolTable resolve(OdinSymbolTable symbolTable, OdinQualifiedType qualifiedType) {
        OdinIdentifier identifier = qualifiedType.getIdentifier();
        OdinSymbol odinSymbol = symbolTable.getSymbol(identifier.getIdentifierToken().getText());
        if (odinSymbol != null) {
            OdinDeclaration odinDeclaration = PsiTreeUtil.getParentOfType(odinSymbol.getDeclaredIdentifier(), false, OdinDeclaration.class);
            if (odinDeclaration instanceof OdinImportDeclarationStatement importDeclarationStatement) {
                return getSymbolsOfImportedPackage(symbolTable.getPackagePath(), importDeclarationStatement);
            }
        }
        return OdinSymbolTable.EMPTY;
    }

}


