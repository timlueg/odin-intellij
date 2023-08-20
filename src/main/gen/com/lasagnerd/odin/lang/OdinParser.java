// This is a generated file. Not intended for manual editing.
package com.lasagnerd.odin.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.lasagnerd.odin.lang.psi.OdinTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class OdinParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return odinFile(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ARGUMENT, NAMED_ARGUMENT, UNNAMED_ARGUMENT),
    create_token_set_(ARRAY_INDEX_EXPRESSION, ARRAY_LITERAL_EXPRESSION, ARRAY_SLICE_EXPRESSION, AUTO_CAST_EXPRESSION,
      BINARY_EXPRESSION, CALL_EXPRESSION, CAST_EXPRESSION, DEREFERENCE_EXPRESSION,
      EXPRESSION, IDENTIFIER_EXPRESSION, IMPLICIT_SELECTOR_EXPRESSION, LITERAL_EXPRESSION,
      MATRIX_INDEX_EXPRESSION, MATRIX_LITERAL_EXPRESSION, NIL_EXPRESSION, OR_RETURN_EXPRESSION,
      PARENTHESIZED_EXPRESSION, PROCEDURE_EXPRESSION, REFERENCE_EXPRESSION, SET_EXPRESSION,
      TAG_STATEMENT_EXPRESSION, TRANSMUTE_EXPRESSION, TYPE_DEFINITION_EXPRESSION, UNARY_EXPRESSION,
      UNINITIALIZED_EXPRESSION),
  };

  /* ********************************************************** */
  // namedArgument
  //                   | unnamedArgument
  public static boolean argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, ARGUMENT, "<argument>");
    r = namedArgument(b, l + 1);
    if (!r) r = unnamedArgument(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // argument (COMMA argument)*
  public static boolean argumentList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT_LIST, "<argument list>");
    r = argument(b, l + 1);
    r = r && argumentList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA argument)*
  private static boolean argumentList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!argumentList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "argumentList_1", c)) break;
    }
    return true;
  }

  // COMMA argument
  private static boolean argumentList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "argumentList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && argument(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET (INTEGER_DEC_LITERAL|QUESTION) RBRACKET LBRACE expression (COMMA expression)* RBRACE
  public static boolean arrayLiteral_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_expression")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && arrayLiteral_expression_1(b, l + 1);
    r = r && consumeTokens(b, 0, RBRACKET, LBRACE);
    r = r && expression(b, l + 1, -1);
    r = r && arrayLiteral_expression_5(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ARRAY_LITERAL_EXPRESSION, r);
    return r;
  }

  // INTEGER_DEC_LITERAL|QUESTION
  private static boolean arrayLiteral_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_expression_1")) return false;
    boolean r;
    r = consumeToken(b, INTEGER_DEC_LITERAL);
    if (!r) r = consumeToken(b, QUESTION);
    return r;
  }

  // (COMMA expression)*
  private static boolean arrayLiteral_expression_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_expression_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!arrayLiteral_expression_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "arrayLiteral_expression_5", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean arrayLiteral_expression_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayLiteral_expression_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET [QUESTION|DYNAMIC|expression] RBRACKET typeDefinition_expression
  public static boolean arrayType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayType")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && arrayType_1(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, ARRAY_TYPE, r);
    return r;
  }

  // [QUESTION|DYNAMIC|expression]
  private static boolean arrayType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayType_1")) return false;
    arrayType_1_0(b, l + 1);
    return true;
  }

  // QUESTION|DYNAMIC|expression
  private static boolean arrayType_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrayType_1_0")) return false;
    boolean r;
    r = consumeToken(b, QUESTION);
    if (!r) r = consumeToken(b, DYNAMIC);
    if (!r) r = expression(b, l + 1, -1);
    return r;
  }

  /* ********************************************************** */
  // EQ
  //                        | PLUS_EQ
  //                        | MINUS_EQ
  //                        | STAR_EQ
  //                        | DIV_EQ
  //                        | MOD_EQ
  //                        | REMAINDER_EQ
  //                        | AND_EQ
  //                        | OR_EQ
  //                        | XOR_EQ
  //                        | ANDAND_EQ
  //                        | OROR_EQ
  //                        | LSHIFT_EQ
  //                        | RSHIFT_EQ
  public static boolean assignmentOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_OPERATOR, "<assignment operator>");
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, PLUS_EQ);
    if (!r) r = consumeToken(b, MINUS_EQ);
    if (!r) r = consumeToken(b, STAR_EQ);
    if (!r) r = consumeToken(b, DIV_EQ);
    if (!r) r = consumeToken(b, MOD_EQ);
    if (!r) r = consumeToken(b, REMAINDER_EQ);
    if (!r) r = consumeToken(b, AND_EQ);
    if (!r) r = consumeToken(b, OR_EQ);
    if (!r) r = consumeToken(b, XOR_EQ);
    if (!r) r = consumeToken(b, ANDAND_EQ);
    if (!r) r = consumeToken(b, OROR_EQ);
    if (!r) r = consumeToken(b, LSHIFT_EQ);
    if (!r) r = consumeToken(b, RSHIFT_EQ);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // leftHandExpressions assignmentOperator expressionsList
  public static boolean assignmentStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "assignmentStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ASSIGNMENT_STATEMENT, "<assignment statement>");
    r = leftHandExpressions(b, l + 1);
    r = r && assignmentOperator(b, l + 1);
    r = r && expressionsList(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // AT IDENTIFIER
  //                        | AT LPAREN IDENTIFIER (EQ (literal_expression | IDENTIFIER))? RPAREN
  public static boolean attributeStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeStatement")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = parseTokens(b, 0, AT, IDENTIFIER);
    if (!r) r = attributeStatement_1(b, l + 1);
    exit_section_(b, m, ATTRIBUTE_STATEMENT, r);
    return r;
  }

  // AT LPAREN IDENTIFIER (EQ (literal_expression | IDENTIFIER))? RPAREN
  private static boolean attributeStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeStatement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, LPAREN, IDENTIFIER);
    r = r && attributeStatement_1_3(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // (EQ (literal_expression | IDENTIFIER))?
  private static boolean attributeStatement_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeStatement_1_3")) return false;
    attributeStatement_1_3_0(b, l + 1);
    return true;
  }

  // EQ (literal_expression | IDENTIFIER)
  private static boolean attributeStatement_1_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeStatement_1_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EQ);
    r = r && attributeStatement_1_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // literal_expression | IDENTIFIER
  private static boolean attributeStatement_1_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attributeStatement_1_3_0_1")) return false;
    boolean r;
    r = literal_expression(b, l + 1);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // PLUS
  //                    | MINUS
  //                    | STAR
  //                    | DIV
  //                    | MOD
  //                    | REMAINDER
  //                    | PIPE
  //                    | TILDE
  //                    | ANDAND
  //                    | ANDNOT
  //                    | AND
  //                    | OROR
  //                    | LSHIFT
  //                    | RSHIFT
  //                    | EQEQ
  //                    | NEQ
  //                    | LT
  //                    | GT
  //                    | LTE
  //                    | GTE
  //                    | RANGE
  //                    | RANGE_INCLUSIVE
  //                    | RANGE_EXCLUSIVE
  //                    | OR_ELSE
  //                    | IN
  //                    | NOT_IN
  public static boolean binaryOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binaryOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINARY_OPERATOR, "<binary operator>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, DIV);
    if (!r) r = consumeToken(b, MOD);
    if (!r) r = consumeToken(b, REMAINDER);
    if (!r) r = consumeToken(b, PIPE);
    if (!r) r = consumeToken(b, TILDE);
    if (!r) r = consumeToken(b, ANDAND);
    if (!r) r = consumeToken(b, ANDNOT);
    if (!r) r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OROR);
    if (!r) r = consumeToken(b, LSHIFT);
    if (!r) r = consumeToken(b, RSHIFT);
    if (!r) r = consumeToken(b, EQEQ);
    if (!r) r = consumeToken(b, NEQ);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, GT);
    if (!r) r = consumeToken(b, LTE);
    if (!r) r = consumeToken(b, GTE);
    if (!r) r = consumeToken(b, RANGE);
    if (!r) r = consumeToken(b, RANGE_INCLUSIVE);
    if (!r) r = consumeToken(b, RANGE_EXCLUSIVE);
    if (!r) r = consumeToken(b, OR_ELSE);
    if (!r) r = consumeToken(b, IN);
    if (!r) r = consumeToken(b, NOT_IN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BIT_SET LBRACKET expression [SEMICOLON identifier_expression] RBRACKET
  public static boolean bitSetType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitSetType")) return false;
    if (!nextTokenIs(b, BIT_SET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, BIT_SET, LBRACKET);
    r = r && expression(b, l + 1, -1);
    r = r && bitSetType_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, BIT_SET_TYPE, r);
    return r;
  }

  // [SEMICOLON identifier_expression]
  private static boolean bitSetType_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitSetType_3")) return false;
    bitSetType_3_0(b, l + 1);
    return true;
  }

  // SEMICOLON identifier_expression
  private static boolean bitSetType_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitSetType_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && identifier_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON COLON BIT_SET LBRACKET expression [SEMICOLON typeDefinition_expression] RBRACKET
  public static boolean bitsetDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitsetDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, COLON, BIT_SET, LBRACKET);
    r = r && expression(b, l + 1, -1);
    r = r && bitsetDeclaration_6(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, BITSET_DECLARATION, r);
    return r;
  }

  // [SEMICOLON typeDefinition_expression]
  private static boolean bitsetDeclaration_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitsetDeclaration_6")) return false;
    bitsetDeclaration_6_0(b, l + 1);
    return true;
  }

  // SEMICOLON typeDefinition_expression
  private static boolean bitsetDeclaration_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bitsetDeclaration_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACE (statementAllowedInBlock)* RBRACE
  public static boolean block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && block_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, BLOCK, r);
    return r;
  }

  // (statementAllowedInBlock)*
  private static boolean block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!block_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "block_1", c)) break;
    }
    return true;
  }

  // (statementAllowedInBlock)
  private static boolean block_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "block_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = statementAllowedInBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TRUE
  //                     | FALSE
  public static boolean boolean_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_literal")) return false;
    if (!nextTokenIs(b, "<boolean literal>", FALSE, TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BOOLEAN_LITERAL, "<boolean literal>");
    r = consumeToken(b, TRUE);
    if (!r) r = consumeToken(b, FALSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BREAK label?
  public static boolean breakStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "breakStatement")) return false;
    if (!nextTokenIs(b, BREAK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BREAK);
    r = r && breakStatement_1(b, l + 1);
    exit_section_(b, m, BREAK_STATEMENT, r);
    return r;
  }

  // label?
  private static boolean breakStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "breakStatement_1")) return false;
    label(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // identifier_expression
  //            | parenthesized_expression
  public static boolean caller(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "caller")) return false;
    if (!nextTokenIs(b, "<caller>", IDENTIFIER, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CALLER, "<caller>");
    r = identifier_expression(b, l + 1);
    if (!r) r = parenthesized_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // expression
  //                   | typeDefinition_expression
  //                   | DOT IDENTIFIER
  public static boolean caseExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "caseExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CASE_EXPRESSION, "<case expression>");
    r = expression(b, l + 1, -1);
    if (!r) r = typeDefinition_expression(b, l + 1);
    if (!r) r = parseTokens(b, 0, DOT, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON typeDefinition_expression? COLON (expression | tagStatement_expression)
  public static boolean constantInitialization(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constantInitialization")) return false;
    if (!nextTokenIs(b, "<constant initialization>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT_INITIALIZATION, "<constant initialization>");
    r = constantInitialization_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && constantInitialization_3(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && constantInitialization_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean constantInitialization_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constantInitialization_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // typeDefinition_expression?
  private static boolean constantInitialization_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constantInitialization_3")) return false;
    typeDefinition_expression(b, l + 1);
    return true;
  }

  // expression | tagStatement_expression
  private static boolean constantInitialization_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "constantInitialization_5")) return false;
    boolean r;
    r = expression(b, l + 1, -1);
    if (!r) r = tagStatement_expression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // CONTINUE label?
  public static boolean continueStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "continueStatement")) return false;
    if (!nextTokenIs(b, CONTINUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CONTINUE);
    r = r && continueStatement_1(b, l + 1);
    exit_section_(b, m, CONTINUE_STATEMENT, r);
    return r;
  }

  // label?
  private static boolean continueStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "continueStatement_1")) return false;
    label(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // DEFER expression
  //                 | ifStatement
  //                 | forStatement
  //                 | switchStatement
  //                 | assignmentStatement
  //                 | block
  public static boolean deferStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deferStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEFER_STATEMENT, "<defer statement>");
    r = deferStatement_0(b, l + 1);
    if (!r) r = ifStatement(b, l + 1);
    if (!r) r = forStatement(b, l + 1);
    if (!r) r = switchStatement(b, l + 1);
    if (!r) r = assignmentStatement(b, l + 1);
    if (!r) r = block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DEFER expression
  private static boolean deferStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deferStatement_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DEFER);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE block
  public static boolean elseBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elseBlock")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ELSE);
    r = r && block(b, l + 1);
    exit_section_(b, m, ELSE_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE IF ifHead block
  public static boolean elseIfBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elseIfBlock")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ELSE, IF);
    r = r && ifHead(b, l + 1);
    r = r && block(b, l + 1);
    exit_section_(b, m, ELSE_IF_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // ELSE WHEN expression block
  public static boolean elseWhenBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "elseWhenBlock")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, ELSE, WHEN);
    r = r && expression(b, l + 1, -1);
    r = r && block(b, l + 1);
    exit_section_(b, m, ELSE_WHEN_BLOCK, r);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON COLON ENUM typeDefinition_expression? enumSpec
  public static boolean enumDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration")) return false;
    if (!nextTokenIs(b, "<enum declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ENUM_DECLARATION, "<enum declaration>");
    r = enumDeclaration_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON, COLON, ENUM);
    r = r && enumDeclaration_5(b, l + 1);
    r = r && enumSpec(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean enumDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // typeDefinition_expression?
  private static boolean enumDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumDeclaration_5")) return false;
    typeDefinition_expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (IDENTIFIER EQ expression COMMA)* RBRACE
  public static boolean enumSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumSpec")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && enumSpec_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, ENUM_SPEC, r);
    return r;
  }

  // (IDENTIFIER EQ expression COMMA)*
  private static boolean enumSpec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumSpec_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!enumSpec_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "enumSpec_1", c)) break;
    }
    return true;
  }

  // IDENTIFIER EQ expression COMMA
  private static boolean enumSpec_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "enumSpec_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQ);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  public static boolean expressionsList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionsList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSIONS_LIST, "<expressions list>");
    r = expression(b, l + 1, -1);
    r = r && expressionsList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA expression)*
  private static boolean expressionsList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionsList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!expressionsList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "expressionsList_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean expressionsList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expressionsList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // USING? IDENTIFIER (COMMA IDENTIFIER)* COLON typeDefinition_expression
  public static boolean fieldDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration")) return false;
    if (!nextTokenIs(b, "<field declaration>", IDENTIFIER, USING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FIELD_DECLARATION, "<field declaration>");
    r = fieldDeclaration_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && fieldDeclaration_2(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // USING?
  private static boolean fieldDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_0")) return false;
    consumeToken(b, USING);
    return true;
  }

  // (COMMA IDENTIFIER)*
  private static boolean fieldDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fieldDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fieldDeclaration_2", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean fieldDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fieldDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // block | forSingleStatement
  public static boolean forBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forBody")) return false;
    if (!nextTokenIs(b, "<for body>", DO, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_BODY, "<for body>");
    r = block(b, l + 1);
    if (!r) r = forSingleStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // assignmentStatement | call_expression
  static boolean forEndStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forEndStatement")) return false;
    boolean r;
    r = assignmentStatement(b, l + 1);
    if (!r) r = call_expression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // forIn|forTraditional
  public static boolean forHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forHead")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = forIn(b, l + 1);
    if (!r) r = forTraditional(b, l + 1);
    exit_section_(b, m, FOR_HEAD, r);
    return r;
  }

  /* ********************************************************** */
  // FOR IDENTIFIER (COMMA IDENTIFIER)* IN expression
  public static boolean forIn(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forIn")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOR, IDENTIFIER);
    r = r && forIn_2(b, l + 1);
    r = r && consumeToken(b, IN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, FOR_IN, r);
    return r;
  }

  // (COMMA IDENTIFIER)*
  private static boolean forIn_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forIn_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!forIn_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "forIn_2", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean forIn_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forIn_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DO statementAllowedInBlock
  public static boolean forSingleStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forSingleStatement")) return false;
    if (!nextTokenIs(b, DO)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DO);
    r = r && statementAllowedInBlock(b, l + 1);
    exit_section_(b, m, FOR_SINGLE_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // variableInitialization | assignmentStatement | call_expression
  static boolean forStartStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStartStatement")) return false;
    boolean r;
    r = variableInitialization(b, l + 1);
    if (!r) r = assignmentStatement(b, l + 1);
    if (!r) r = call_expression(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // label? tagStatement_expression? forHead forBody
  public static boolean forStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOR_STATEMENT, "<for statement>");
    r = forStatement_0(b, l + 1);
    r = r && forStatement_1(b, l + 1);
    r = r && forHead(b, l + 1);
    r = r && forBody(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // label?
  private static boolean forStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_0")) return false;
    label(b, l + 1);
    return true;
  }

  // tagStatement_expression?
  private static boolean forStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forStatement_1")) return false;
    tagStatement_expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // FOR (forStartStatement? SEMICOLON)? expression? (SEMICOLON forEndStatement?)?
  public static boolean forTraditional(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, FOR);
    r = r && forTraditional_1(b, l + 1);
    r = r && forTraditional_2(b, l + 1);
    r = r && forTraditional_3(b, l + 1);
    exit_section_(b, m, FOR_TRADITIONAL, r);
    return r;
  }

  // (forStartStatement? SEMICOLON)?
  private static boolean forTraditional_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_1")) return false;
    forTraditional_1_0(b, l + 1);
    return true;
  }

  // forStartStatement? SEMICOLON
  private static boolean forTraditional_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = forTraditional_1_0_0(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // forStartStatement?
  private static boolean forTraditional_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_1_0_0")) return false;
    forStartStatement(b, l + 1);
    return true;
  }

  // expression?
  private static boolean forTraditional_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_2")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // (SEMICOLON forEndStatement?)?
  private static boolean forTraditional_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_3")) return false;
    forTraditional_3_0(b, l + 1);
    return true;
  }

  // SEMICOLON forEndStatement?
  private static boolean forTraditional_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    r = r && forTraditional_3_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // forEndStatement?
  private static boolean forTraditional_3_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "forTraditional_3_0_1")) return false;
    forEndStatement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (foreignProcedureDeclaration|variableDeclaration) RBRACE
  public static boolean foreignBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignBlock")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && foreignBlock_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, FOREIGN_BLOCK, r);
    return r;
  }

  // foreignProcedureDeclaration|variableDeclaration
  private static boolean foreignBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignBlock_1")) return false;
    boolean r;
    r = foreignProcedureDeclaration(b, l + 1);
    if (!r) r = variableDeclaration(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // FOREIGN IMPORT ( IDENTIFIER? DQ_STRING_LITERAL | LBRACE DQ_STRING_LITERAL (COMMA DQ_STRING_LITERAL)* RBRACE)
  public static boolean foreignImportDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration")) return false;
    if (!nextTokenIs(b, FOREIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, FOREIGN, IMPORT);
    r = r && foreignImportDeclaration_2(b, l + 1);
    exit_section_(b, m, FOREIGN_IMPORT_DECLARATION, r);
    return r;
  }

  // IDENTIFIER? DQ_STRING_LITERAL | LBRACE DQ_STRING_LITERAL (COMMA DQ_STRING_LITERAL)* RBRACE
  private static boolean foreignImportDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = foreignImportDeclaration_2_0(b, l + 1);
    if (!r) r = foreignImportDeclaration_2_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER? DQ_STRING_LITERAL
  private static boolean foreignImportDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = foreignImportDeclaration_2_0_0(b, l + 1);
    r = r && consumeToken(b, DQ_STRING_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  // IDENTIFIER?
  private static boolean foreignImportDeclaration_2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2_0_0")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  // LBRACE DQ_STRING_LITERAL (COMMA DQ_STRING_LITERAL)* RBRACE
  private static boolean foreignImportDeclaration_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACE, DQ_STRING_LITERAL);
    r = r && foreignImportDeclaration_2_1_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA DQ_STRING_LITERAL)*
  private static boolean foreignImportDeclaration_2_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2_1_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!foreignImportDeclaration_2_1_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "foreignImportDeclaration_2_1_2", c)) break;
    }
    return true;
  }

  // COMMA DQ_STRING_LITERAL
  private static boolean foreignImportDeclaration_2_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignImportDeclaration_2_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, DQ_STRING_LITERAL);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON COLON PROC string_literal? LPAREN parameterList* RPAREN (ARROW returnType)? TRIPLE_DASH
  public static boolean foreignProcedureDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration")) return false;
    if (!nextTokenIs(b, "<foreign procedure declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOREIGN_PROCEDURE_DECLARATION, "<foreign procedure declaration>");
    r = foreignProcedureDeclaration_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON, COLON, PROC);
    r = r && foreignProcedureDeclaration_5(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && foreignProcedureDeclaration_7(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && foreignProcedureDeclaration_9(b, l + 1);
    r = r && consumeToken(b, TRIPLE_DASH);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean foreignProcedureDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // string_literal?
  private static boolean foreignProcedureDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration_5")) return false;
    string_literal(b, l + 1);
    return true;
  }

  // parameterList*
  private static boolean foreignProcedureDeclaration_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "foreignProcedureDeclaration_7", c)) break;
    }
    return true;
  }

  // (ARROW returnType)?
  private static boolean foreignProcedureDeclaration_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration_9")) return false;
    foreignProcedureDeclaration_9_0(b, l + 1);
    return true;
  }

  // ARROW returnType
  private static boolean foreignProcedureDeclaration_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignProcedureDeclaration_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARROW);
    r = r && returnType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? FOREIGN IDENTIFIER foreignBlock
  public static boolean foreignStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignStatement")) return false;
    if (!nextTokenIs(b, "<foreign statement>", AT, FOREIGN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOREIGN_STATEMENT, "<foreign statement>");
    r = foreignStatement_0(b, l + 1);
    r = r && consumeTokens(b, 0, FOREIGN, IDENTIFIER);
    r = r && foreignBlock(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean foreignStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "foreignStatement_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // foreignImportDeclaration
  //                 | importDeclaration
  //                 | constantInitialization
  //                 | procedureDeclaration
  //                 | enumDeclaration
  //                 | unionDeclaration
  //                 | typeAliasDeclaration
  //                 | structDeclaration
  //                 | bitsetDeclaration
  //                 | variableDeclaration
  //                 | variableInitialization
  //                 | procedureOverloadStatement
  //                 | foreignStatement
  //                 | tagStatement_expression
  public static boolean globalStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "globalStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GLOBAL_STATEMENT, "<global statement>");
    r = foreignImportDeclaration(b, l + 1);
    if (!r) r = importDeclaration(b, l + 1);
    if (!r) r = constantInitialization(b, l + 1);
    if (!r) r = procedureDeclaration(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    if (!r) r = unionDeclaration(b, l + 1);
    if (!r) r = typeAliasDeclaration(b, l + 1);
    if (!r) r = structDeclaration(b, l + 1);
    if (!r) r = bitsetDeclaration(b, l + 1);
    if (!r) r = variableDeclaration(b, l + 1);
    if (!r) r = variableInitialization(b, l + 1);
    if (!r) r = procedureOverloadStatement(b, l + 1);
    if (!r) r = foreignStatement(b, l + 1);
    if (!r) r = tagStatement_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER (COMMA IDENTIFIER)*
  public static boolean identifierList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierList")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && identifierList_1(b, l + 1);
    exit_section_(b, m, IDENTIFIER_LIST, r);
    return r;
  }

  // (COMMA IDENTIFIER)*
  private static boolean identifierList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!identifierList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "identifierList_1", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean identifierList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifierList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (variableInitialization (COMMA variableInitialization)* SEMICOLON)? expression
  public static boolean ifHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifHead")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, IF_HEAD, "<if head>");
    r = ifHead_0(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (variableInitialization (COMMA variableInitialization)* SEMICOLON)?
  private static boolean ifHead_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifHead_0")) return false;
    ifHead_0_0(b, l + 1);
    return true;
  }

  // variableInitialization (COMMA variableInitialization)* SEMICOLON
  private static boolean ifHead_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifHead_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableInitialization(b, l + 1);
    r = r && ifHead_0_0_1(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA variableInitialization)*
  private static boolean ifHead_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifHead_0_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ifHead_0_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ifHead_0_0_1", c)) break;
    }
    return true;
  }

  // COMMA variableInitialization
  private static boolean ifHead_0_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifHead_0_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && variableInitialization(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IF  ifHead block (elseIfBlock)* (elseBlock)?
  public static boolean ifStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IF);
    r = r && ifHead(b, l + 1);
    r = r && block(b, l + 1);
    r = r && ifStatement_3(b, l + 1);
    r = r && ifStatement_4(b, l + 1);
    exit_section_(b, m, IF_STATEMENT, r);
    return r;
  }

  // (elseIfBlock)*
  private static boolean ifStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ifStatement_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ifStatement_3", c)) break;
    }
    return true;
  }

  // (elseIfBlock)
  private static boolean ifStatement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = elseIfBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (elseBlock)?
  private static boolean ifStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_4")) return false;
    ifStatement_4_0(b, l + 1);
    return true;
  }

  // (elseBlock)
  private static boolean ifStatement_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ifStatement_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = elseBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IMPORT IDENTIFIER? DQ_STRING_LITERAL
  public static boolean importDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importDeclaration")) return false;
    if (!nextTokenIs(b, IMPORT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IMPORT);
    r = r && importDeclaration_1(b, l + 1);
    r = r && consumeToken(b, DQ_STRING_LITERAL);
    exit_section_(b, m, IMPORT_DECLARATION, r);
    return r;
  }

  // IDENTIFIER?
  private static boolean importDeclaration_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "importDeclaration_1")) return false;
    consumeToken(b, IDENTIFIER);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON
  static boolean label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "label")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // matrixIndex_expression
  //     | arrayIndex_expression
  //     | arraySlice_expression
  //     | reference_expression
  //     | identifier_expression
  //     | dereference_expression
  public static boolean leftHandExpression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leftHandExpression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LEFT_HAND_EXPRESSION, "<left hand expression>");
    r = matrixIndex_expression(b, l + 1);
    if (!r) r = expression(b, l + 1, 5);
    if (!r) r = expression(b, l + 1, 4);
    if (!r) r = expression(b, l + 1, 1);
    if (!r) r = identifier_expression(b, l + 1);
    if (!r) r = expression(b, l + 1, 11);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // leftHandExpression (COMMA leftHandExpression)*
  public static boolean leftHandExpressions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leftHandExpressions")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LEFT_HAND_EXPRESSIONS, "<left hand expressions>");
    r = leftHandExpression(b, l + 1);
    r = r && leftHandExpressions_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA leftHandExpression)*
  private static boolean leftHandExpressions_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leftHandExpressions_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!leftHandExpressions_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "leftHandExpressions_1", c)) break;
    }
    return true;
  }

  // COMMA leftHandExpression
  private static boolean leftHandExpressions_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "leftHandExpressions_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && leftHandExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MAP LBRACKET typeDefinition_expression RBRACKET typeDefinition_expression
  public static boolean mapType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "mapType")) return false;
    if (!nextTokenIs(b, MAP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MAP, LBRACKET);
    r = r && typeDefinition_expression(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, MAP_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // expression LBRACKET expression COMMA expression RBRACKET
  public static boolean matrixIndex_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "matrixIndex_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, MATRIX_INDEX_EXPRESSION, "<matrix index expression>");
    r = expression(b, l + 1, -1);
    r = r && consumeToken(b, LBRACKET);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // MATRIX LBRACKET INTEGER_DEC_LITERAL COMMA INTEGER_DEC_LITERAL RBRACKET LBRACE expression (COMMA expression)* RBRACE
  public static boolean matrixLiteral_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "matrixLiteral_expression")) return false;
    if (!nextTokenIs(b, MATRIX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MATRIX, LBRACKET, INTEGER_DEC_LITERAL, COMMA, INTEGER_DEC_LITERAL, RBRACKET, LBRACE);
    r = r && expression(b, l + 1, -1);
    r = r && matrixLiteral_expression_8(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, MATRIX_LITERAL_EXPRESSION, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean matrixLiteral_expression_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "matrixLiteral_expression_8")) return false;
    while (true) {
      int c = current_position_(b);
      if (!matrixLiteral_expression_8_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "matrixLiteral_expression_8", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean matrixLiteral_expression_8_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "matrixLiteral_expression_8_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MATRIX LBRACKET expression COMMA expression RBRACKET typeDefinition_expression
  public static boolean matrixType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "matrixType")) return false;
    if (!nextTokenIs(b, MATRIX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, MATRIX, LBRACKET);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RBRACKET);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, MATRIX_TYPE, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER EQ expression
  public static boolean namedArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namedArgument")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, EQ);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, NAMED_ARGUMENT, r);
    return r;
  }

  /* ********************************************************** */
  // INTEGER_DEC_LITERAL
  //                     | INTEGER_HEX_LITERAL
  //                     | INTEGER_OCT_LITERAL
  //                     | INTEGER_BIN_LITERAL
  //                     | FLOAT_DEC_LITERAL
  //                     | COMPLEX_INTEGER_DEC_LITERAL
  //                     | COMPLEX_FLOAT_LITERAL
  public static boolean numeric_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "numeric_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, NUMERIC_LITERAL, "<numeric literal>");
    r = consumeToken(b, INTEGER_DEC_LITERAL);
    if (!r) r = consumeToken(b, INTEGER_HEX_LITERAL);
    if (!r) r = consumeToken(b, INTEGER_OCT_LITERAL);
    if (!r) r = consumeToken(b, INTEGER_BIN_LITERAL);
    if (!r) r = consumeToken(b, FLOAT_DEC_LITERAL);
    if (!r) r = consumeToken(b, COMPLEX_INTEGER_DEC_LITERAL);
    if (!r) r = consumeToken(b, COMPLEX_FLOAT_LITERAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // packageDeclaration globalStatement*
  static boolean odinFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "odinFile")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = packageDeclaration(b, l + 1);
    r = r && odinFile_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // globalStatement*
  private static boolean odinFile_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "odinFile_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!globalStatement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "odinFile_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PACKAGE IDENTIFIER
  public static boolean packageDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "packageDeclaration")) return false;
    if (!nextTokenIs(b, PACKAGE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PACKAGE, IDENTIFIER);
    exit_section_(b, m, PACKAGE_DECLARATION, r);
    return r;
  }

  /* ********************************************************** */
  // parameterDeclaration
  //               | parameterInitialization
  //               | variadicParameter
  public static boolean parameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER, "<parameter>");
    r = parameterDeclaration(b, l + 1);
    if (!r) r = parameterInitialization(b, l + 1);
    if (!r) r = variadicParameter(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // [USING|DOLLAR] IDENTIFIER (COMMA DOLLAR? IDENTIFIER)* COLON expression parameterTypeSpecialization?
  public static boolean parameterDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_DECLARATION, "<parameter declaration>");
    r = parameterDeclaration_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && parameterDeclaration_2(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && expression(b, l + 1, -1);
    r = r && parameterDeclaration_5(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [USING|DOLLAR]
  private static boolean parameterDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_0")) return false;
    parameterDeclaration_0_0(b, l + 1);
    return true;
  }

  // USING|DOLLAR
  private static boolean parameterDeclaration_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_0_0")) return false;
    boolean r;
    r = consumeToken(b, USING);
    if (!r) r = consumeToken(b, DOLLAR);
    return r;
  }

  // (COMMA DOLLAR? IDENTIFIER)*
  private static boolean parameterDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterDeclaration_2", c)) break;
    }
    return true;
  }

  // COMMA DOLLAR? IDENTIFIER
  private static boolean parameterDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameterDeclaration_2_0_1(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOLLAR?
  private static boolean parameterDeclaration_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_2_0_1")) return false;
    consumeToken(b, DOLLAR);
    return true;
  }

  // parameterTypeSpecialization?
  private static boolean parameterDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterDeclaration_5")) return false;
    parameterTypeSpecialization(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON EQ expression
  static boolean parameterInitialization(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterInitialization")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, EQ);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // parameter (COMMA parameter)*
  public static boolean parameterList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PARAMETER_LIST, "<parameter list>");
    r = parameter(b, l + 1);
    r = r && parameterList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA parameter)*
  private static boolean parameterList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "parameterList_1", c)) break;
    }
    return true;
  }

  // COMMA parameter
  private static boolean parameterList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && parameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DIV expression
  public static boolean parameterTypeSpecialization(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parameterTypeSpecialization")) return false;
    if (!nextTokenIs(b, DIV)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DIV);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, PARAMETER_TYPE_SPECIALIZATION, r);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON COLON PROC string_literal? LPAREN parameterList* RPAREN (ARROW returnType)? block
  public static boolean procedureDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration")) return false;
    if (!nextTokenIs(b, "<procedure declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROCEDURE_DECLARATION, "<procedure declaration>");
    r = procedureDeclaration_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON, COLON, PROC);
    r = r && procedureDeclaration_5(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && procedureDeclaration_7(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && procedureDeclaration_9(b, l + 1);
    r = r && block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean procedureDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // string_literal?
  private static boolean procedureDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration_5")) return false;
    string_literal(b, l + 1);
    return true;
  }

  // parameterList*
  private static boolean procedureDeclaration_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedureDeclaration_7", c)) break;
    }
    return true;
  }

  // (ARROW returnType)?
  private static boolean procedureDeclaration_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration_9")) return false;
    procedureDeclaration_9_0(b, l + 1);
    return true;
  }

  // ARROW returnType
  private static boolean procedureDeclaration_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureDeclaration_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARROW);
    r = r && returnType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON COLON PROC LBRACE IDENTIFIER (COMMA IDENTIFIER)* RBRACE
  public static boolean procedureOverloadStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureOverloadStatement")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, COLON, PROC, LBRACE, IDENTIFIER);
    r = r && procedureOverloadStatement_6(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, PROCEDURE_OVERLOAD_STATEMENT, r);
    return r;
  }

  // (COMMA IDENTIFIER)*
  private static boolean procedureOverloadStatement_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureOverloadStatement_6")) return false;
    while (true) {
      int c = current_position_(b);
      if (!procedureOverloadStatement_6_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedureOverloadStatement_6", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean procedureOverloadStatement_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureOverloadStatement_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PROC LPAREN parameterList* RPAREN (ARROW returnType)?
  public static boolean procedureType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureType")) return false;
    if (!nextTokenIs(b, PROC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PROC, LPAREN);
    r = r && procedureType_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && procedureType_4(b, l + 1);
    exit_section_(b, m, PROCEDURE_TYPE, r);
    return r;
  }

  // parameterList*
  private static boolean procedureType_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureType_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedureType_2", c)) break;
    }
    return true;
  }

  // (ARROW returnType)?
  private static boolean procedureType_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureType_4")) return false;
    procedureType_4_0(b, l + 1);
    return true;
  }

  // ARROW returnType
  private static boolean procedureType_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedureType_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ARROW);
    r = r && returnType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expression (COMMA expression)*
  static boolean returnArgumentList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnArgumentList")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && returnArgumentList_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean returnArgumentList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnArgumentList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!returnArgumentList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "returnArgumentList_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean returnArgumentList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnArgumentList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (identifierList COLON)? expression
  public static boolean returnParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameter")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_PARAMETER, "<return parameter>");
    r = returnParameter_0(b, l + 1);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (identifierList COLON)?
  private static boolean returnParameter_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameter_0")) return false;
    returnParameter_0_0(b, l + 1);
    return true;
  }

  // identifierList COLON
  private static boolean returnParameter_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameter_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierList(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // returnParameter (COMMA returnParameter)*
  public static boolean returnParameterList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterList")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_PARAMETER_LIST, "<return parameter list>");
    r = returnParameter(b, l + 1);
    r = r && returnParameterList_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA returnParameter)*
  private static boolean returnParameterList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!returnParameterList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "returnParameterList_1", c)) break;
    }
    return true;
  }

  // COMMA returnParameter
  private static boolean returnParameterList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnParameterList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && returnParameter(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // RETURN returnArgumentList?
  public static boolean returnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RETURN);
    r = r && returnStatement_1(b, l + 1);
    exit_section_(b, m, RETURN_STATEMENT, r);
    return r;
  }

  // returnArgumentList?
  private static boolean returnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnStatement_1")) return false;
    returnArgumentList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // typeDefinition_expression | LPAREN returnParameterList RPAREN
  public static boolean returnType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RETURN_TYPE, "<return type>");
    r = typeDefinition_expression(b, l + 1);
    if (!r) r = returnType_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // LPAREN returnParameterList RPAREN
  private static boolean returnType_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "returnType_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LPAREN);
    r = r && returnParameterList(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // procedureDeclaration
  //                | variableInitialization
  //                | enumDeclaration
  //                | variableDeclaration
  //                | constantInitialization
  //                | procedureOverloadStatement
  //                | bitsetDeclaration
  //                | forStatement
  //                | ifStatement
  //                | whenStatement
  //                | switchStatement
  //                | deferStatement
  //                | returnStatement
  //                | breakStatement
  //                | continueStatement
  //                | attributeStatement
  //                | usingStatement
  //                | tagStatement_expression
  public static boolean statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = procedureDeclaration(b, l + 1);
    if (!r) r = variableInitialization(b, l + 1);
    if (!r) r = enumDeclaration(b, l + 1);
    if (!r) r = variableDeclaration(b, l + 1);
    if (!r) r = constantInitialization(b, l + 1);
    if (!r) r = procedureOverloadStatement(b, l + 1);
    if (!r) r = bitsetDeclaration(b, l + 1);
    if (!r) r = forStatement(b, l + 1);
    if (!r) r = ifStatement(b, l + 1);
    if (!r) r = whenStatement(b, l + 1);
    if (!r) r = switchStatement(b, l + 1);
    if (!r) r = deferStatement(b, l + 1);
    if (!r) r = returnStatement(b, l + 1);
    if (!r) r = breakStatement(b, l + 1);
    if (!r) r = continueStatement(b, l + 1);
    if (!r) r = attributeStatement(b, l + 1);
    if (!r) r = usingStatement(b, l + 1);
    if (!r) r = tagStatement_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // statement|assignmentStatement|expression
  public static boolean statementAllowedInBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "statementAllowedInBlock")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT_ALLOWED_IN_BLOCK, "<statement allowed in block>");
    r = statement(b, l + 1);
    if (!r) r = assignmentStatement(b, l + 1);
    if (!r) r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // DQ_STRING_LITERAL
  //                     | SQ_STRING_LITERAL
  //                     | RAW_STRING_LITERAL
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, DQ_STRING_LITERAL);
    if (!r) r = consumeToken(b, SQ_STRING_LITERAL);
    if (!r) r = consumeToken(b, RAW_STRING_LITERAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON COLON STRUCT tagStatement_expression? structSpec
  public static boolean structDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structDeclaration")) return false;
    if (!nextTokenIs(b, "<struct declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRUCT_DECLARATION, "<struct declaration>");
    r = structDeclaration_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON, COLON, STRUCT);
    r = r && structDeclaration_5(b, l + 1);
    r = r && structSpec(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean structDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // tagStatement_expression?
  private static boolean structDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structDeclaration_5")) return false;
    tagStatement_expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE (fieldDeclaration COMMA)* RBRACE
  public static boolean structSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structSpec")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && structSpec_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, STRUCT_SPEC, r);
    return r;
  }

  // (fieldDeclaration COMMA)*
  private static boolean structSpec_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structSpec_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!structSpec_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "structSpec_1", c)) break;
    }
    return true;
  }

  // fieldDeclaration COMMA
  private static boolean structSpec_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "structSpec_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fieldDeclaration(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CASE (caseExpression(COMMA caseExpression)*)? COLON (statementAllowedInBlock|FALLTHROUGH)*
  public static boolean switchCaseBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock")) return false;
    if (!nextTokenIs(b, CASE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CASE);
    r = r && switchCaseBlock_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && switchCaseBlock_3(b, l + 1);
    exit_section_(b, m, SWITCH_CASE_BLOCK, r);
    return r;
  }

  // (caseExpression(COMMA caseExpression)*)?
  private static boolean switchCaseBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_1")) return false;
    switchCaseBlock_1_0(b, l + 1);
    return true;
  }

  // caseExpression(COMMA caseExpression)*
  private static boolean switchCaseBlock_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = caseExpression(b, l + 1);
    r = r && switchCaseBlock_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA caseExpression)*
  private static boolean switchCaseBlock_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!switchCaseBlock_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchCaseBlock_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA caseExpression
  private static boolean switchCaseBlock_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && caseExpression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (statementAllowedInBlock|FALLTHROUGH)*
  private static boolean switchCaseBlock_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!switchCaseBlock_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchCaseBlock_3", c)) break;
    }
    return true;
  }

  // statementAllowedInBlock|FALLTHROUGH
  private static boolean switchCaseBlock_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchCaseBlock_3_0")) return false;
    boolean r;
    r = statementAllowedInBlock(b, l + 1);
    if (!r) r = consumeToken(b, FALLTHROUGH);
    return r;
  }

  /* ********************************************************** */
  // tagStatement_expression? SWITCH IN? ifHead? LBRACE (switchCaseBlock)* RBRACE
  public static boolean switchStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement")) return false;
    if (!nextTokenIs(b, "<switch statement>", HASH, SWITCH)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SWITCH_STATEMENT, "<switch statement>");
    r = switchStatement_0(b, l + 1);
    r = r && consumeToken(b, SWITCH);
    r = r && switchStatement_2(b, l + 1);
    r = r && switchStatement_3(b, l + 1);
    r = r && consumeToken(b, LBRACE);
    r = r && switchStatement_5(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // tagStatement_expression?
  private static boolean switchStatement_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement_0")) return false;
    tagStatement_expression(b, l + 1);
    return true;
  }

  // IN?
  private static boolean switchStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement_2")) return false;
    consumeToken(b, IN);
    return true;
  }

  // ifHead?
  private static boolean switchStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement_3")) return false;
    ifHead(b, l + 1);
    return true;
  }

  // (switchCaseBlock)*
  private static boolean switchStatement_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!switchStatement_5_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "switchStatement_5", c)) break;
    }
    return true;
  }

  // (switchCaseBlock)
  private static boolean switchStatement_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "switchStatement_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = switchCaseBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HASH IDENTIFIER
  public static boolean tagHead(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagHead")) return false;
    if (!nextTokenIs(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, HASH, IDENTIFIER);
    exit_section_(b, m, TAG_HEAD, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON COLON DISTINCT? typeDefinition_expression
  public static boolean typeAliasDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeAliasDeclaration")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, COLON);
    r = r && typeAliasDeclaration_3(b, l + 1);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, TYPE_ALIAS_DECLARATION, r);
    return r;
  }

  // DISTINCT?
  private static boolean typeAliasDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeAliasDeclaration_3")) return false;
    consumeToken(b, DISTINCT);
    return true;
  }

  /* ********************************************************** */
  // mapType
  //                      | matrixType
  //                      | bitSetType
  //                      | arrayType
  //                      | procedureType
  //                      | (DOLLAR? IDENTIFIER)
  static boolean typeType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeType")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = mapType(b, l + 1);
    if (!r) r = matrixType(b, l + 1);
    if (!r) r = bitSetType(b, l + 1);
    if (!r) r = arrayType(b, l + 1);
    if (!r) r = procedureType(b, l + 1);
    if (!r) r = typeType_5(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOLLAR? IDENTIFIER
  private static boolean typeType_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeType_5")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = typeType_5_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  // DOLLAR?
  private static boolean typeType_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeType_5_0")) return false;
    consumeToken(b, DOLLAR);
    return true;
  }

  /* ********************************************************** */
  // PLUS      // Arithmetic identity
  //                   | MINUS   // Arithmetic negation
  //                   | NOT     // Boolean not
  //                   | TILDE   // Bitwise not
  //                   | AND
  public static boolean unaryOperator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unaryOperator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNARY_OPERATOR, "<unary operator>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, TILDE);
    if (!r) r = consumeToken(b, AND);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER COLON COLON UNION tagStatement_expression? unionSpec
  public static boolean unionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionDeclaration")) return false;
    if (!nextTokenIs(b, "<union declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNION_DECLARATION, "<union declaration>");
    r = unionDeclaration_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON, COLON, UNION);
    r = r && unionDeclaration_5(b, l + 1);
    r = r && unionSpec(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean unionDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // tagStatement_expression?
  private static boolean unionDeclaration_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionDeclaration_5")) return false;
    tagStatement_expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE typeDefinition_expression (COMMA typeDefinition_expression)* RBRACE
  public static boolean unionSpec(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionSpec")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && typeDefinition_expression(b, l + 1);
    r = r && unionSpec_2(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, UNION_SPEC, r);
    return r;
  }

  // (COMMA typeDefinition_expression)*
  private static boolean unionSpec_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionSpec_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!unionSpec_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "unionSpec_2", c)) break;
    }
    return true;
  }

  // COMMA typeDefinition_expression
  private static boolean unionSpec_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unionSpec_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expression
  public static boolean unnamedArgument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unnamedArgument")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, UNNAMED_ARGUMENT, "<unnamed argument>");
    r = expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // USING expression
  public static boolean usingStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "usingStatement")) return false;
    if (!nextTokenIs(b, USING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, USING);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, USING_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // attributeStatement? IDENTIFIER (COMMA IDENTIFIER)* COLON typeDefinition_expression
  public static boolean variableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration")) return false;
    if (!nextTokenIs(b, "<variable declaration>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_DECLARATION, "<variable declaration>");
    r = variableDeclaration_0(b, l + 1);
    r = r && consumeToken(b, IDENTIFIER);
    r = r && variableDeclaration_2(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && typeDefinition_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // attributeStatement?
  private static boolean variableDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // (COMMA IDENTIFIER)*
  private static boolean variableDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!variableDeclaration_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "variableDeclaration_2", c)) break;
    }
    return true;
  }

  // COMMA IDENTIFIER
  private static boolean variableDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // identifierList COLON EQ expressionsList
  //                            | attributeStatement? IDENTIFIER COLON typeDefinition_expression? EQ expression
  public static boolean variableInitialization(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitialization")) return false;
    if (!nextTokenIs(b, "<variable initialization>", AT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VARIABLE_INITIALIZATION, "<variable initialization>");
    r = variableInitialization_0(b, l + 1);
    if (!r) r = variableInitialization_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // identifierList COLON EQ expressionsList
  private static boolean variableInitialization_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitialization_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = identifierList(b, l + 1);
    r = r && consumeTokens(b, 0, COLON, EQ);
    r = r && expressionsList(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attributeStatement? IDENTIFIER COLON typeDefinition_expression? EQ expression
  private static boolean variableInitialization_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitialization_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = variableInitialization_1_0(b, l + 1);
    r = r && consumeTokens(b, 0, IDENTIFIER, COLON);
    r = r && variableInitialization_1_3(b, l + 1);
    r = r && consumeToken(b, EQ);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // attributeStatement?
  private static boolean variableInitialization_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitialization_1_0")) return false;
    attributeStatement(b, l + 1);
    return true;
  }

  // typeDefinition_expression?
  private static boolean variableInitialization_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variableInitialization_1_3")) return false;
    typeDefinition_expression(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON RANGE expression
  static boolean variadicParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "variadicParameter")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON, RANGE);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // WHEN expression block (elseWhenBlock)* (elseBlock)?
  public static boolean whenStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whenStatement")) return false;
    if (!nextTokenIs(b, WHEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHEN);
    r = r && expression(b, l + 1, -1);
    r = r && block(b, l + 1);
    r = r && whenStatement_3(b, l + 1);
    r = r && whenStatement_4(b, l + 1);
    exit_section_(b, m, WHEN_STATEMENT, r);
    return r;
  }

  // (elseWhenBlock)*
  private static boolean whenStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whenStatement_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!whenStatement_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "whenStatement_3", c)) break;
    }
    return true;
  }

  // (elseWhenBlock)
  private static boolean whenStatement_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whenStatement_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = elseWhenBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (elseBlock)?
  private static boolean whenStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whenStatement_4")) return false;
    whenStatement_4_0(b, l + 1);
    return true;
  }

  // (elseBlock)
  private static boolean whenStatement_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "whenStatement_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = elseBlock(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Expression root: expression
  // Operator priority table:
  // 0: PREFIX(implicitSelector_expression)
  // 1: ATOM(procedure_expression)
  // 2: BINARY(reference_expression)
  // 3: PREFIX(parenthesized_expression)
  // 4: ATOM(call_expression)
  // 5: POSTFIX(arraySlice_expression)
  // 6: BINARY(arrayIndex_expression)
  // 7: BINARY(binary_expression)
  // 8: PREFIX(unary_expression)
  // 9: POSTFIX(or_return_expression)
  // 10: ATOM(set_expression)
  // 11: ATOM(literal_expression)
  // 12: POSTFIX(dereference_expression)
  // 13: ATOM(uninitialized_expression)
  // 14: ATOM(cast_expression)
  // 15: ATOM(transmute_expression)
  // 16: PREFIX(auto_cast_expression)
  // 17: ATOM(nil_expression)
  // 18: ATOM(identifier_expression)
  // 19: ATOM(typeDefinition_expression)
  // 20: ATOM(tagStatement_expression)
  public static boolean expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = implicitSelector_expression(b, l + 1);
    if (!r) r = procedure_expression(b, l + 1);
    if (!r) r = parenthesized_expression(b, l + 1);
    if (!r) r = call_expression(b, l + 1);
    if (!r) r = unary_expression(b, l + 1);
    if (!r) r = set_expression(b, l + 1);
    if (!r) r = literal_expression(b, l + 1);
    if (!r) r = uninitialized_expression(b, l + 1);
    if (!r) r = cast_expression(b, l + 1);
    if (!r) r = transmute_expression(b, l + 1);
    if (!r) r = auto_cast_expression(b, l + 1);
    if (!r) r = nil_expression(b, l + 1);
    if (!r) r = identifier_expression(b, l + 1);
    if (!r) r = typeDefinition_expression(b, l + 1);
    if (!r) r = tagStatement_expression(b, l + 1);
    p = r;
    r = r && expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 2 && consumeTokenSmart(b, DOT)) {
        r = expression(b, l, 2);
        exit_section_(b, l, m, REFERENCE_EXPRESSION, r, true, null);
      }
      else if (g < 5 && arraySlice_expression_0(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, ARRAY_SLICE_EXPRESSION, r, true, null);
      }
      else if (g < 6 && consumeTokenSmart(b, LBRACKET)) {
        r = report_error_(b, expression(b, l, 6));
        r = consumeToken(b, RBRACKET) && r;
        exit_section_(b, l, m, ARRAY_INDEX_EXPRESSION, r, true, null);
      }
      else if (g < 7 && binaryOperator(b, l + 1)) {
        r = expression(b, l, 7);
        exit_section_(b, l, m, BINARY_EXPRESSION, r, true, null);
      }
      else if (g < 9 && consumeTokenSmart(b, OR_RETURN)) {
        r = true;
        exit_section_(b, l, m, OR_RETURN_EXPRESSION, r, true, null);
      }
      else if (g < 12 && consumeTokenSmart(b, CARET)) {
        r = true;
        exit_section_(b, l, m, DEREFERENCE_EXPRESSION, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  public static boolean implicitSelector_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implicitSelector_expression")) return false;
    if (!nextTokenIsSmart(b, DOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, DOT);
    p = r;
    r = p && expression(b, l, 0);
    exit_section_(b, l, m, IMPLICIT_SELECTOR_EXPRESSION, r, p, null);
    return r || p;
  }

  // PROC LPAREN parameterList* RPAREN (ARROW returnType)? block
  public static boolean procedure_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_expression")) return false;
    if (!nextTokenIsSmart(b, PROC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, PROC, LPAREN);
    r = r && procedure_expression_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && procedure_expression_4(b, l + 1);
    r = r && block(b, l + 1);
    exit_section_(b, m, PROCEDURE_EXPRESSION, r);
    return r;
  }

  // parameterList*
  private static boolean procedure_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_expression_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!parameterList(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "procedure_expression_2", c)) break;
    }
    return true;
  }

  // (ARROW returnType)?
  private static boolean procedure_expression_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_expression_4")) return false;
    procedure_expression_4_0(b, l + 1);
    return true;
  }

  // ARROW returnType
  private static boolean procedure_expression_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "procedure_expression_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, ARROW);
    r = r && returnType(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean parenthesized_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "parenthesized_expression")) return false;
    if (!nextTokenIsSmart(b, LPAREN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, LPAREN);
    p = r;
    r = p && expression(b, l, 3);
    r = p && report_error_(b, consumeToken(b, RPAREN)) && r;
    exit_section_(b, l, m, PARENTHESIZED_EXPRESSION, r, p, null);
    return r || p;
  }

  // caller LPAREN argumentList? RPAREN
  public static boolean call_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_expression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER, LPAREN)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CALL_EXPRESSION, "<call expression>");
    r = caller(b, l + 1);
    r = r && consumeToken(b, LPAREN);
    r = r && call_expression_2(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // argumentList?
  private static boolean call_expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "call_expression_2")) return false;
    argumentList(b, l + 1);
    return true;
  }

  // LBRACKET expression? COLON expression? RBRACKET
  private static boolean arraySlice_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arraySlice_expression_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBRACKET);
    r = r && arraySlice_expression_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && arraySlice_expression_0_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // expression?
  private static boolean arraySlice_expression_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arraySlice_expression_0_1")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  // expression?
  private static boolean arraySlice_expression_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arraySlice_expression_0_3")) return false;
    expression(b, l + 1, -1);
    return true;
  }

  public static boolean unary_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "unary_expression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = unaryOperator(b, l + 1);
    p = r;
    r = p && expression(b, l, 8);
    exit_section_(b, l, m, UNARY_EXPRESSION, r, p, null);
    return r || p;
  }

  // LBRACE [expression (COMMA expression)*] RBRACE
  public static boolean set_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_expression")) return false;
    if (!nextTokenIsSmart(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LBRACE);
    r = r && set_expression_1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, SET_EXPRESSION, r);
    return r;
  }

  // [expression (COMMA expression)*]
  private static boolean set_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_expression_1")) return false;
    set_expression_1_0(b, l + 1);
    return true;
  }

  // expression (COMMA expression)*
  private static boolean set_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1, -1);
    r = r && set_expression_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA expression)*
  private static boolean set_expression_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_expression_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!set_expression_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "set_expression_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA expression
  private static boolean set_expression_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "set_expression_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, COMMA);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  // string_literal
  //          | numeric_literal
  //          | boolean_literal
  //          | matrixLiteral_expression
  //          | arrayLiteral_expression
  public static boolean literal_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "literal_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _COLLAPSE_, LITERAL_EXPRESSION, "<literal expression>");
    r = string_literal(b, l + 1);
    if (!r) r = numeric_literal(b, l + 1);
    if (!r) r = boolean_literal(b, l + 1);
    if (!r) r = matrixLiteral_expression(b, l + 1);
    if (!r) r = arrayLiteral_expression(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TRIPLE_DASH
  public static boolean uninitialized_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "uninitialized_expression")) return false;
    if (!nextTokenIsSmart(b, TRIPLE_DASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, TRIPLE_DASH);
    exit_section_(b, m, UNINITIALIZED_EXPRESSION, r);
    return r;
  }

  // CAST LPAREN typeDefinition_expression RPAREN expression
  public static boolean cast_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cast_expression")) return false;
    if (!nextTokenIsSmart(b, CAST)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, CAST, LPAREN);
    r = r && typeDefinition_expression(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, CAST_EXPRESSION, r);
    return r;
  }

  // TRANSMUTE LPAREN expression RPAREN expression
  public static boolean transmute_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "transmute_expression")) return false;
    if (!nextTokenIsSmart(b, TRANSMUTE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokensSmart(b, 0, TRANSMUTE, LPAREN);
    r = r && expression(b, l + 1, -1);
    r = r && consumeToken(b, RPAREN);
    r = r && expression(b, l + 1, -1);
    exit_section_(b, m, TRANSMUTE_EXPRESSION, r);
    return r;
  }

  public static boolean auto_cast_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "auto_cast_expression")) return false;
    if (!nextTokenIsSmart(b, AUTO_CAST)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = consumeTokenSmart(b, AUTO_CAST);
    p = r;
    r = p && expression(b, l, 16);
    exit_section_(b, l, m, AUTO_CAST_EXPRESSION, r, p, null);
    return r || p;
  }

  // NIL
  public static boolean nil_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nil_expression")) return false;
    if (!nextTokenIsSmart(b, NIL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, NIL);
    exit_section_(b, m, NIL_EXPRESSION, r);
    return r;
  }

  // IDENTIFIER
  public static boolean identifier_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "identifier_expression")) return false;
    if (!nextTokenIsSmart(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, IDENTIFIER);
    exit_section_(b, m, IDENTIFIER_EXPRESSION, r);
    return r;
  }

  // CARET? typeType
  public static boolean typeDefinition_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDefinition_expression")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_DEFINITION_EXPRESSION, "<type definition expression>");
    r = typeDefinition_expression_0(b, l + 1);
    r = r && typeType(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // CARET?
  private static boolean typeDefinition_expression_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "typeDefinition_expression_0")) return false;
    consumeTokenSmart(b, CARET);
    return true;
  }

  // tagHead ( literal_expression | (LPAREN expressionsList? RPAREN))?
  public static boolean tagStatement_expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagStatement_expression")) return false;
    if (!nextTokenIsSmart(b, HASH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = tagHead(b, l + 1);
    r = r && tagStatement_expression_1(b, l + 1);
    exit_section_(b, m, TAG_STATEMENT_EXPRESSION, r);
    return r;
  }

  // ( literal_expression | (LPAREN expressionsList? RPAREN))?
  private static boolean tagStatement_expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagStatement_expression_1")) return false;
    tagStatement_expression_1_0(b, l + 1);
    return true;
  }

  // literal_expression | (LPAREN expressionsList? RPAREN)
  private static boolean tagStatement_expression_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagStatement_expression_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = literal_expression(b, l + 1);
    if (!r) r = tagStatement_expression_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LPAREN expressionsList? RPAREN
  private static boolean tagStatement_expression_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagStatement_expression_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, LPAREN);
    r = r && tagStatement_expression_1_0_1_1(b, l + 1);
    r = r && consumeToken(b, RPAREN);
    exit_section_(b, m, null, r);
    return r;
  }

  // expressionsList?
  private static boolean tagStatement_expression_1_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tagStatement_expression_1_0_1_1")) return false;
    expressionsList(b, l + 1);
    return true;
  }

}
