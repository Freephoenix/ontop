// $ANTLR 3.3 Nov 30, 2010 12:50:56 SQL99.g 2011-06-21 16:24:46

package it.unibz.krdb.obda.parser;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SQL99Parser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SELECT", "DISTINCT", "ALL", "ASTERISK", "COMMA", "DOT", "AS", "COUNT", "LPAREN", "RPAREN", "AVG", "MAX", "MIN", "SUM", "EVERY", "ANY", "SOME", "FROM", "WHERE", "OR", "AND", "IS", "NOT", "NULL", "IN", "GROUP", "BY", "JOIN", "STRING", "STRING_WITH_QUOTE_DOUBLE", "TRUE", "FALSE", "NUMERIC", "STRING_WITH_QUOTE", "EQUALS", "LESS", "GREATER", "ORDER", "ON", "LEFT", "RIGHT", "SEMI", "LSQ_BRACKET", "RSQ_BRACKET", "QUESTION", "DOLLAR", "QUOTE_DOUBLE", "QUOTE_SINGLE", "APOSTROPHE", "UNDERSCORE", "DASH", "AMPERSAND", "AT", "EXCLAMATION", "HASH", "PERCENT", "PLUS", "COLON", "SLASH", "DOUBLE_SLASH", "BACKSLASH", "TILDE", "CARET", "ALPHA", "DIGIT", "ALPHANUM", "CHAR", "WS"
    };
    public static final int EOF=-1;
    public static final int SELECT=4;
    public static final int DISTINCT=5;
    public static final int ALL=6;
    public static final int ASTERISK=7;
    public static final int COMMA=8;
    public static final int DOT=9;
    public static final int AS=10;
    public static final int COUNT=11;
    public static final int LPAREN=12;
    public static final int RPAREN=13;
    public static final int AVG=14;
    public static final int MAX=15;
    public static final int MIN=16;
    public static final int SUM=17;
    public static final int EVERY=18;
    public static final int ANY=19;
    public static final int SOME=20;
    public static final int FROM=21;
    public static final int WHERE=22;
    public static final int OR=23;
    public static final int AND=24;
    public static final int IS=25;
    public static final int NOT=26;
    public static final int NULL=27;
    public static final int IN=28;
    public static final int GROUP=29;
    public static final int BY=30;
    public static final int JOIN=31;
    public static final int STRING=32;
    public static final int STRING_WITH_QUOTE_DOUBLE=33;
    public static final int TRUE=34;
    public static final int FALSE=35;
    public static final int NUMERIC=36;
    public static final int STRING_WITH_QUOTE=37;
    public static final int EQUALS=38;
    public static final int LESS=39;
    public static final int GREATER=40;
    public static final int ORDER=41;
    public static final int ON=42;
    public static final int LEFT=43;
    public static final int RIGHT=44;
    public static final int SEMI=45;
    public static final int LSQ_BRACKET=46;
    public static final int RSQ_BRACKET=47;
    public static final int QUESTION=48;
    public static final int DOLLAR=49;
    public static final int QUOTE_DOUBLE=50;
    public static final int QUOTE_SINGLE=51;
    public static final int APOSTROPHE=52;
    public static final int UNDERSCORE=53;
    public static final int DASH=54;
    public static final int AMPERSAND=55;
    public static final int AT=56;
    public static final int EXCLAMATION=57;
    public static final int HASH=58;
    public static final int PERCENT=59;
    public static final int PLUS=60;
    public static final int COLON=61;
    public static final int SLASH=62;
    public static final int DOUBLE_SLASH=63;
    public static final int BACKSLASH=64;
    public static final int TILDE=65;
    public static final int CARET=66;
    public static final int ALPHA=67;
    public static final int DIGIT=68;
    public static final int ALPHANUM=69;
    public static final int CHAR=70;
    public static final int WS=71;

    // delegates
    // delegators


        public SQL99Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public SQL99Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return SQL99Parser.tokenNames; }
    public String getGrammarFileName() { return "SQL99.g"; }



    // $ANTLR start "parse"
    // SQL99.g:15:1: parse : query EOF ;
    public final void parse() throws RecognitionException {
        try {
            // SQL99.g:16:3: ( query EOF )
            // SQL99.g:16:5: query EOF
            {
            pushFollow(FOLLOW_query_in_parse30);
            query();

            state._fsp--;

            match(input,EOF,FOLLOW_EOF_in_parse32); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "parse"


    // $ANTLR start "query"
    // SQL99.g:19:1: query : SELECT ( set_quantifier )? select_list table_expression ;
    public final void query() throws RecognitionException {
        try {
            // SQL99.g:20:3: ( SELECT ( set_quantifier )? select_list table_expression )
            // SQL99.g:20:5: SELECT ( set_quantifier )? select_list table_expression
            {
            match(input,SELECT,FOLLOW_SELECT_in_query47); 
            // SQL99.g:20:12: ( set_quantifier )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>=DISTINCT && LA1_0<=ALL)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // SQL99.g:20:12: set_quantifier
                    {
                    pushFollow(FOLLOW_set_quantifier_in_query49);
                    set_quantifier();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_select_list_in_query52);
            select_list();

            state._fsp--;

            pushFollow(FOLLOW_table_expression_in_query54);
            table_expression();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "query"


    // $ANTLR start "set_quantifier"
    // SQL99.g:23:1: set_quantifier : ( DISTINCT | ALL );
    public final void set_quantifier() throws RecognitionException {
        try {
            // SQL99.g:24:3: ( DISTINCT | ALL )
            // SQL99.g:
            {
            if ( (input.LA(1)>=DISTINCT && input.LA(1)<=ALL) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "set_quantifier"


    // $ANTLR start "select_list"
    // SQL99.g:28:1: select_list : ( ASTERISK | select_sublist ( COMMA select_sublist )* );
    public final void select_list() throws RecognitionException {
        try {
            // SQL99.g:29:3: ( ASTERISK | select_sublist ( COMMA select_sublist )* )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ASTERISK) ) {
                alt3=1;
            }
            else if ( (LA3_0==COUNT||(LA3_0>=AVG && LA3_0<=SOME)||(LA3_0>=STRING && LA3_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // SQL99.g:29:5: ASTERISK
                    {
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_select_list91); 

                    }
                    break;
                case 2 :
                    // SQL99.g:30:5: select_sublist ( COMMA select_sublist )*
                    {
                    pushFollow(FOLLOW_select_sublist_in_select_list97);
                    select_sublist();

                    state._fsp--;

                    // SQL99.g:30:20: ( COMMA select_sublist )*
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( (LA2_0==COMMA) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // SQL99.g:30:21: COMMA select_sublist
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_select_list100); 
                    	    pushFollow(FOLLOW_select_sublist_in_select_list102);
                    	    select_sublist();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop2;
                        }
                    } while (true);


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "select_list"


    // $ANTLR start "select_sublist"
    // SQL99.g:33:1: select_sublist : ( qualified_asterisk | derived_column );
    public final void select_sublist() throws RecognitionException {
        try {
            // SQL99.g:34:3: ( qualified_asterisk | derived_column )
            int alt4=2;
            switch ( input.LA(1) ) {
            case STRING:
                {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==DOT) ) {
                    int LA4_4 = input.LA(3);

                    if ( (LA4_4==ASTERISK) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_4>=STRING && LA4_4<=STRING_WITH_QUOTE_DOUBLE)) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_1==COMMA||LA4_1==AS||LA4_1==FROM||(LA4_1>=STRING && LA4_1<=STRING_WITH_QUOTE_DOUBLE)) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
                }
                break;
            case STRING_WITH_QUOTE_DOUBLE:
                {
                int LA4_2 = input.LA(2);

                if ( (LA4_2==DOT) ) {
                    int LA4_4 = input.LA(3);

                    if ( (LA4_4==ASTERISK) ) {
                        alt4=1;
                    }
                    else if ( ((LA4_4>=STRING && LA4_4<=STRING_WITH_QUOTE_DOUBLE)) ) {
                        alt4=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 4, input);

                        throw nvae;
                    }
                }
                else if ( (LA4_2==COMMA||LA4_2==AS||LA4_2==FROM||(LA4_2>=STRING && LA4_2<=STRING_WITH_QUOTE_DOUBLE)) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;
                }
                }
                break;
            case COUNT:
            case AVG:
            case MAX:
            case MIN:
            case SUM:
            case EVERY:
            case ANY:
            case SOME:
                {
                alt4=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // SQL99.g:34:5: qualified_asterisk
                    {
                    pushFollow(FOLLOW_qualified_asterisk_in_select_sublist119);
                    qualified_asterisk();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:35:5: derived_column
                    {
                    pushFollow(FOLLOW_derived_column_in_select_sublist125);
                    derived_column();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "select_sublist"


    // $ANTLR start "qualified_asterisk"
    // SQL99.g:38:1: qualified_asterisk : table_identifier DOT ASTERISK ;
    public final void qualified_asterisk() throws RecognitionException {
        try {
            // SQL99.g:39:3: ( table_identifier DOT ASTERISK )
            // SQL99.g:39:5: table_identifier DOT ASTERISK
            {
            pushFollow(FOLLOW_table_identifier_in_qualified_asterisk140);
            table_identifier();

            state._fsp--;

            match(input,DOT,FOLLOW_DOT_in_qualified_asterisk142); 
            match(input,ASTERISK,FOLLOW_ASTERISK_in_qualified_asterisk144); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "qualified_asterisk"


    // $ANTLR start "derived_column"
    // SQL99.g:42:1: derived_column : value_expression ( ( AS )? alias_name )? ;
    public final void derived_column() throws RecognitionException {
        try {
            // SQL99.g:43:3: ( value_expression ( ( AS )? alias_name )? )
            // SQL99.g:43:5: value_expression ( ( AS )? alias_name )?
            {
            pushFollow(FOLLOW_value_expression_in_derived_column159);
            value_expression();

            state._fsp--;

            // SQL99.g:43:22: ( ( AS )? alias_name )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==AS||(LA6_0>=STRING && LA6_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // SQL99.g:43:23: ( AS )? alias_name
                    {
                    // SQL99.g:43:23: ( AS )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0==AS) ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // SQL99.g:43:23: AS
                            {
                            match(input,AS,FOLLOW_AS_in_derived_column162); 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_alias_name_in_derived_column165);
                    alias_name();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "derived_column"


    // $ANTLR start "value_expression"
    // SQL99.g:46:1: value_expression : ( reference_value_expression | collection_value_expression );
    public final void value_expression() throws RecognitionException {
        try {
            // SQL99.g:47:3: ( reference_value_expression | collection_value_expression )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( ((LA7_0>=STRING && LA7_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt7=1;
            }
            else if ( (LA7_0==COUNT||(LA7_0>=AVG && LA7_0<=SOME)) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // SQL99.g:47:5: reference_value_expression
                    {
                    pushFollow(FOLLOW_reference_value_expression_in_value_expression183);
                    reference_value_expression();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:48:5: collection_value_expression
                    {
                    pushFollow(FOLLOW_collection_value_expression_in_value_expression189);
                    collection_value_expression();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "value_expression"


    // $ANTLR start "reference_value_expression"
    // SQL99.g:51:1: reference_value_expression : column_reference ;
    public final void reference_value_expression() throws RecognitionException {
        try {
            // SQL99.g:52:3: ( column_reference )
            // SQL99.g:52:5: column_reference
            {
            pushFollow(FOLLOW_column_reference_in_reference_value_expression202);
            column_reference();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "reference_value_expression"


    // $ANTLR start "column_reference"
    // SQL99.g:55:1: column_reference : ( table_identifier DOT )? column_name ;
    public final void column_reference() throws RecognitionException {
        try {
            // SQL99.g:56:3: ( ( table_identifier DOT )? column_name )
            // SQL99.g:56:5: ( table_identifier DOT )? column_name
            {
            // SQL99.g:56:5: ( table_identifier DOT )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==STRING) ) {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==DOT) ) {
                    alt8=1;
                }
            }
            else if ( (LA8_0==STRING_WITH_QUOTE_DOUBLE) ) {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==DOT) ) {
                    alt8=1;
                }
            }
            switch (alt8) {
                case 1 :
                    // SQL99.g:56:6: table_identifier DOT
                    {
                    pushFollow(FOLLOW_table_identifier_in_column_reference216);
                    table_identifier();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_column_reference218); 

                    }
                    break;

            }

            pushFollow(FOLLOW_column_name_in_column_reference222);
            column_name();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "column_reference"


    // $ANTLR start "collection_value_expression"
    // SQL99.g:59:1: collection_value_expression : set_function_specification ;
    public final void collection_value_expression() throws RecognitionException {
        try {
            // SQL99.g:60:3: ( set_function_specification )
            // SQL99.g:60:5: set_function_specification
            {
            pushFollow(FOLLOW_set_function_specification_in_collection_value_expression239);
            set_function_specification();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "collection_value_expression"


    // $ANTLR start "set_function_specification"
    // SQL99.g:63:1: set_function_specification : ( COUNT LPAREN ASTERISK RPAREN | general_set_function );
    public final void set_function_specification() throws RecognitionException {
        try {
            // SQL99.g:64:3: ( COUNT LPAREN ASTERISK RPAREN | general_set_function )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==COUNT) ) {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==LPAREN) ) {
                    int LA9_3 = input.LA(3);

                    if ( (LA9_3==ASTERISK) ) {
                        alt9=1;
                    }
                    else if ( ((LA9_3>=DISTINCT && LA9_3<=ALL)||LA9_3==COUNT||(LA9_3>=AVG && LA9_3<=SOME)||(LA9_3>=STRING && LA9_3<=STRING_WITH_QUOTE_DOUBLE)) ) {
                        alt9=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 3, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;
                }
            }
            else if ( ((LA9_0>=AVG && LA9_0<=SOME)) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // SQL99.g:64:5: COUNT LPAREN ASTERISK RPAREN
                    {
                    match(input,COUNT,FOLLOW_COUNT_in_set_function_specification252); 
                    match(input,LPAREN,FOLLOW_LPAREN_in_set_function_specification254); 
                    match(input,ASTERISK,FOLLOW_ASTERISK_in_set_function_specification256); 
                    match(input,RPAREN,FOLLOW_RPAREN_in_set_function_specification258); 

                    }
                    break;
                case 2 :
                    // SQL99.g:65:5: general_set_function
                    {
                    pushFollow(FOLLOW_general_set_function_in_set_function_specification264);
                    general_set_function();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "set_function_specification"


    // $ANTLR start "general_set_function"
    // SQL99.g:68:1: general_set_function : set_function_op LPAREN ( set_quantifier )? value_expression RPAREN ;
    public final void general_set_function() throws RecognitionException {
        try {
            // SQL99.g:69:3: ( set_function_op LPAREN ( set_quantifier )? value_expression RPAREN )
            // SQL99.g:69:5: set_function_op LPAREN ( set_quantifier )? value_expression RPAREN
            {
            pushFollow(FOLLOW_set_function_op_in_general_set_function279);
            set_function_op();

            state._fsp--;

            match(input,LPAREN,FOLLOW_LPAREN_in_general_set_function281); 
            // SQL99.g:69:28: ( set_quantifier )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( ((LA10_0>=DISTINCT && LA10_0<=ALL)) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // SQL99.g:69:29: set_quantifier
                    {
                    pushFollow(FOLLOW_set_quantifier_in_general_set_function284);
                    set_quantifier();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_value_expression_in_general_set_function288);
            value_expression();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_general_set_function290); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "general_set_function"


    // $ANTLR start "set_function_op"
    // SQL99.g:72:1: set_function_op : ( AVG | MAX | MIN | SUM | EVERY | ANY | SOME | COUNT );
    public final void set_function_op() throws RecognitionException {
        try {
            // SQL99.g:73:3: ( AVG | MAX | MIN | SUM | EVERY | ANY | SOME | COUNT )
            // SQL99.g:
            {
            if ( input.LA(1)==COUNT||(input.LA(1)>=AVG && input.LA(1)<=SOME) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "set_function_op"


    // $ANTLR start "table_expression"
    // SQL99.g:83:1: table_expression : from_clause ( where_clause )? ( group_by_clause )? ;
    public final void table_expression() throws RecognitionException {
        try {
            // SQL99.g:84:3: ( from_clause ( where_clause )? ( group_by_clause )? )
            // SQL99.g:84:5: from_clause ( where_clause )? ( group_by_clause )?
            {
            pushFollow(FOLLOW_from_clause_in_table_expression366);
            from_clause();

            state._fsp--;

            // SQL99.g:84:17: ( where_clause )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==WHERE) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // SQL99.g:84:18: where_clause
                    {
                    pushFollow(FOLLOW_where_clause_in_table_expression369);
                    where_clause();

                    state._fsp--;


                    }
                    break;

            }

            // SQL99.g:84:33: ( group_by_clause )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==GROUP) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // SQL99.g:84:34: group_by_clause
                    {
                    pushFollow(FOLLOW_group_by_clause_in_table_expression374);
                    group_by_clause();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_expression"


    // $ANTLR start "from_clause"
    // SQL99.g:87:1: from_clause : FROM table_reference_list ;
    public final void from_clause() throws RecognitionException {
        try {
            // SQL99.g:88:3: ( FROM table_reference_list )
            // SQL99.g:88:5: FROM table_reference_list
            {
            match(input,FROM,FOLLOW_FROM_in_from_clause391); 
            pushFollow(FOLLOW_table_reference_list_in_from_clause393);
            table_reference_list();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "from_clause"


    // $ANTLR start "table_reference_list"
    // SQL99.g:91:1: table_reference_list : table_reference ( COMMA table_reference )* ;
    public final void table_reference_list() throws RecognitionException {
        try {
            // SQL99.g:92:3: ( table_reference ( COMMA table_reference )* )
            // SQL99.g:92:5: table_reference ( COMMA table_reference )*
            {
            pushFollow(FOLLOW_table_reference_in_table_reference_list410);
            table_reference();

            state._fsp--;

            // SQL99.g:92:21: ( COMMA table_reference )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==COMMA) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // SQL99.g:92:22: COMMA table_reference
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_table_reference_list413); 
            	    pushFollow(FOLLOW_table_reference_in_table_reference_list415);
            	    table_reference();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_reference_list"


    // $ANTLR start "table_reference"
    // SQL99.g:95:1: table_reference : ( table_primary | joined_table );
    public final void table_reference() throws RecognitionException {
        try {
            // SQL99.g:96:3: ( table_primary | joined_table )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>=STRING && LA14_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt14=1;
            }
            else if ( (LA14_0==JOIN) ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // SQL99.g:96:5: table_primary
                    {
                    pushFollow(FOLLOW_table_primary_in_table_reference432);
                    table_primary();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:97:5: joined_table
                    {
                    pushFollow(FOLLOW_joined_table_in_table_reference439);
                    joined_table();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_reference"


    // $ANTLR start "where_clause"
    // SQL99.g:100:1: where_clause : WHERE search_condition ;
    public final void where_clause() throws RecognitionException {
        try {
            // SQL99.g:101:3: ( WHERE search_condition )
            // SQL99.g:101:5: WHERE search_condition
            {
            match(input,WHERE,FOLLOW_WHERE_in_where_clause452); 
            pushFollow(FOLLOW_search_condition_in_where_clause454);
            search_condition();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "where_clause"


    // $ANTLR start "search_condition"
    // SQL99.g:104:1: search_condition : boolean_value_expression ;
    public final void search_condition() throws RecognitionException {
        try {
            // SQL99.g:105:3: ( boolean_value_expression )
            // SQL99.g:105:5: boolean_value_expression
            {
            pushFollow(FOLLOW_boolean_value_expression_in_search_condition467);
            boolean_value_expression();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "search_condition"


    // $ANTLR start "boolean_value_expression"
    // SQL99.g:108:1: boolean_value_expression : boolean_term ( ( OR | AND ) boolean_term )* ;
    public final void boolean_value_expression() throws RecognitionException {
        try {
            // SQL99.g:109:3: ( boolean_term ( ( OR | AND ) boolean_term )* )
            // SQL99.g:109:5: boolean_term ( ( OR | AND ) boolean_term )*
            {
            pushFollow(FOLLOW_boolean_term_in_boolean_value_expression482);
            boolean_term();

            state._fsp--;

            // SQL99.g:109:18: ( ( OR | AND ) boolean_term )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=OR && LA15_0<=AND)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // SQL99.g:109:19: ( OR | AND ) boolean_term
            	    {
            	    if ( (input.LA(1)>=OR && input.LA(1)<=AND) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_boolean_term_in_boolean_value_expression491);
            	    boolean_term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "boolean_value_expression"


    // $ANTLR start "boolean_term"
    // SQL99.g:112:1: boolean_term : predicate ;
    public final void boolean_term() throws RecognitionException {
        try {
            // SQL99.g:113:3: ( predicate )
            // SQL99.g:113:5: predicate
            {
            pushFollow(FOLLOW_predicate_in_boolean_term506);
            predicate();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "boolean_term"


    // $ANTLR start "predicate"
    // SQL99.g:116:1: predicate : ( comparison_predicate | null_predicate | in_predicate );
    public final void predicate() throws RecognitionException {
        try {
            // SQL99.g:117:3: ( comparison_predicate | null_predicate | in_predicate )
            int alt16=3;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==STRING) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA16_3 = input.LA(3);

                    if ( (LA16_3==STRING) ) {
                        switch ( input.LA(4) ) {
                        case EQUALS:
                        case LESS:
                        case GREATER:
                            {
                            alt16=1;
                            }
                            break;
                        case IS:
                            {
                            alt16=2;
                            }
                            break;
                        case NOT:
                        case IN:
                            {
                            alt16=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA16_3==STRING_WITH_QUOTE_DOUBLE) ) {
                        switch ( input.LA(4) ) {
                        case EQUALS:
                        case LESS:
                        case GREATER:
                            {
                            alt16=1;
                            }
                            break;
                        case IS:
                            {
                            alt16=2;
                            }
                            break;
                        case NOT:
                        case IN:
                            {
                            alt16=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 8, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EQUALS:
                case LESS:
                case GREATER:
                    {
                    alt16=1;
                    }
                    break;
                case IS:
                    {
                    alt16=2;
                    }
                    break;
                case NOT:
                case IN:
                    {
                    alt16=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 1, input);

                    throw nvae;
                }

            }
            else if ( (LA16_0==STRING_WITH_QUOTE_DOUBLE) ) {
                switch ( input.LA(2) ) {
                case DOT:
                    {
                    int LA16_3 = input.LA(3);

                    if ( (LA16_3==STRING) ) {
                        switch ( input.LA(4) ) {
                        case EQUALS:
                        case LESS:
                        case GREATER:
                            {
                            alt16=1;
                            }
                            break;
                        case IS:
                            {
                            alt16=2;
                            }
                            break;
                        case NOT:
                        case IN:
                            {
                            alt16=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 7, input);

                            throw nvae;
                        }

                    }
                    else if ( (LA16_3==STRING_WITH_QUOTE_DOUBLE) ) {
                        switch ( input.LA(4) ) {
                        case EQUALS:
                        case LESS:
                        case GREATER:
                            {
                            alt16=1;
                            }
                            break;
                        case IS:
                            {
                            alt16=2;
                            }
                            break;
                        case NOT:
                        case IN:
                            {
                            alt16=3;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 16, 8, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 16, 3, input);

                        throw nvae;
                    }
                    }
                    break;
                case EQUALS:
                case LESS:
                case GREATER:
                    {
                    alt16=1;
                    }
                    break;
                case IS:
                    {
                    alt16=2;
                    }
                    break;
                case NOT:
                case IN:
                    {
                    alt16=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 2, input);

                    throw nvae;
                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // SQL99.g:117:5: comparison_predicate
                    {
                    pushFollow(FOLLOW_comparison_predicate_in_predicate521);
                    comparison_predicate();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:118:5: null_predicate
                    {
                    pushFollow(FOLLOW_null_predicate_in_predicate527);
                    null_predicate();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // SQL99.g:119:5: in_predicate
                    {
                    pushFollow(FOLLOW_in_predicate_in_predicate533);
                    in_predicate();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "predicate"


    // $ANTLR start "comparison_predicate"
    // SQL99.g:122:1: comparison_predicate : ( column_reference comp_op value | column_reference comp_op column_reference );
    public final void comparison_predicate() throws RecognitionException {
        try {
            // SQL99.g:123:3: ( column_reference comp_op value | column_reference comp_op column_reference )
            int alt17=2;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // SQL99.g:123:5: column_reference comp_op value
                    {
                    pushFollow(FOLLOW_column_reference_in_comparison_predicate548);
                    column_reference();

                    state._fsp--;

                    pushFollow(FOLLOW_comp_op_in_comparison_predicate550);
                    comp_op();

                    state._fsp--;

                    pushFollow(FOLLOW_value_in_comparison_predicate552);
                    value();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:124:5: column_reference comp_op column_reference
                    {
                    pushFollow(FOLLOW_column_reference_in_comparison_predicate558);
                    column_reference();

                    state._fsp--;

                    pushFollow(FOLLOW_comp_op_in_comparison_predicate560);
                    comp_op();

                    state._fsp--;

                    pushFollow(FOLLOW_column_reference_in_comparison_predicate562);
                    column_reference();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "comparison_predicate"


    // $ANTLR start "comp_op"
    // SQL99.g:127:1: comp_op : ( equals_operator | not_equals_operator | less_than_operator | greater_than_operator | less_than_or_equals_operator | greater_than_or_equals_operator );
    public final void comp_op() throws RecognitionException {
        try {
            // SQL99.g:128:3: ( equals_operator | not_equals_operator | less_than_operator | greater_than_operator | less_than_or_equals_operator | greater_than_or_equals_operator )
            int alt18=6;
            switch ( input.LA(1) ) {
            case EQUALS:
                {
                alt18=1;
                }
                break;
            case LESS:
                {
                switch ( input.LA(2) ) {
                case GREATER:
                    {
                    alt18=2;
                    }
                    break;
                case EQUALS:
                    {
                    alt18=5;
                    }
                    break;
                case STRING:
                case STRING_WITH_QUOTE_DOUBLE:
                case TRUE:
                case FALSE:
                case NUMERIC:
                case STRING_WITH_QUOTE:
                    {
                    alt18=3;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 2, input);

                    throw nvae;
                }

                }
                break;
            case GREATER:
                {
                int LA18_3 = input.LA(2);

                if ( (LA18_3==EQUALS) ) {
                    alt18=6;
                }
                else if ( ((LA18_3>=STRING && LA18_3<=STRING_WITH_QUOTE)) ) {
                    alt18=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 3, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // SQL99.g:128:5: equals_operator
                    {
                    pushFollow(FOLLOW_equals_operator_in_comp_op575);
                    equals_operator();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:129:5: not_equals_operator
                    {
                    pushFollow(FOLLOW_not_equals_operator_in_comp_op581);
                    not_equals_operator();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // SQL99.g:130:5: less_than_operator
                    {
                    pushFollow(FOLLOW_less_than_operator_in_comp_op587);
                    less_than_operator();

                    state._fsp--;


                    }
                    break;
                case 4 :
                    // SQL99.g:131:5: greater_than_operator
                    {
                    pushFollow(FOLLOW_greater_than_operator_in_comp_op593);
                    greater_than_operator();

                    state._fsp--;


                    }
                    break;
                case 5 :
                    // SQL99.g:132:5: less_than_or_equals_operator
                    {
                    pushFollow(FOLLOW_less_than_or_equals_operator_in_comp_op599);
                    less_than_or_equals_operator();

                    state._fsp--;


                    }
                    break;
                case 6 :
                    // SQL99.g:133:5: greater_than_or_equals_operator
                    {
                    pushFollow(FOLLOW_greater_than_or_equals_operator_in_comp_op605);
                    greater_than_or_equals_operator();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "comp_op"


    // $ANTLR start "null_predicate"
    // SQL99.g:136:1: null_predicate : column_reference IS ( NOT )? NULL ;
    public final void null_predicate() throws RecognitionException {
        try {
            // SQL99.g:137:3: ( column_reference IS ( NOT )? NULL )
            // SQL99.g:137:5: column_reference IS ( NOT )? NULL
            {
            pushFollow(FOLLOW_column_reference_in_null_predicate618);
            column_reference();

            state._fsp--;

            match(input,IS,FOLLOW_IS_in_null_predicate620); 
            // SQL99.g:137:25: ( NOT )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==NOT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // SQL99.g:137:26: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_null_predicate623); 

                    }
                    break;

            }

            match(input,NULL,FOLLOW_NULL_in_null_predicate627); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "null_predicate"


    // $ANTLR start "in_predicate"
    // SQL99.g:140:1: in_predicate : column_reference ( NOT )? IN in_predicate_value ;
    public final void in_predicate() throws RecognitionException {
        try {
            // SQL99.g:141:3: ( column_reference ( NOT )? IN in_predicate_value )
            // SQL99.g:141:5: column_reference ( NOT )? IN in_predicate_value
            {
            pushFollow(FOLLOW_column_reference_in_in_predicate640);
            column_reference();

            state._fsp--;

            // SQL99.g:141:22: ( NOT )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==NOT) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // SQL99.g:141:23: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_in_predicate643); 

                    }
                    break;

            }

            match(input,IN,FOLLOW_IN_in_in_predicate647); 
            pushFollow(FOLLOW_in_predicate_value_in_in_predicate649);
            in_predicate_value();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "in_predicate"


    // $ANTLR start "in_predicate_value"
    // SQL99.g:144:1: in_predicate_value : ( table_subquery | LPAREN value_list RPAREN );
    public final void in_predicate_value() throws RecognitionException {
        try {
            // SQL99.g:145:3: ( table_subquery | LPAREN value_list RPAREN )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==LPAREN) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==SELECT) ) {
                    alt21=1;
                }
                else if ( ((LA21_1>=TRUE && LA21_1<=STRING_WITH_QUOTE)) ) {
                    alt21=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // SQL99.g:145:5: table_subquery
                    {
                    pushFollow(FOLLOW_table_subquery_in_in_predicate_value664);
                    table_subquery();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:146:5: LPAREN value_list RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_in_predicate_value670); 
                    pushFollow(FOLLOW_value_list_in_in_predicate_value672);
                    value_list();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_in_predicate_value674); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "in_predicate_value"


    // $ANTLR start "table_subquery"
    // SQL99.g:149:1: table_subquery : LPAREN query RPAREN ;
    public final void table_subquery() throws RecognitionException {
        try {
            // SQL99.g:150:3: ( LPAREN query RPAREN )
            // SQL99.g:150:5: LPAREN query RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_table_subquery687); 
            pushFollow(FOLLOW_query_in_table_subquery689);
            query();

            state._fsp--;

            match(input,RPAREN,FOLLOW_RPAREN_in_table_subquery691); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_subquery"


    // $ANTLR start "value_list"
    // SQL99.g:153:1: value_list : value ( COMMA value )* ;
    public final void value_list() throws RecognitionException {
        try {
            // SQL99.g:154:3: ( value ( COMMA value )* )
            // SQL99.g:154:5: value ( COMMA value )*
            {
            pushFollow(FOLLOW_value_in_value_list706);
            value();

            state._fsp--;

            // SQL99.g:154:11: ( COMMA value )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // SQL99.g:154:12: COMMA value
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_value_list709); 
            	    pushFollow(FOLLOW_value_in_value_list711);
            	    value();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "value_list"


    // $ANTLR start "group_by_clause"
    // SQL99.g:157:1: group_by_clause : GROUP BY grouping_element_list ;
    public final void group_by_clause() throws RecognitionException {
        try {
            // SQL99.g:158:3: ( GROUP BY grouping_element_list )
            // SQL99.g:158:5: GROUP BY grouping_element_list
            {
            match(input,GROUP,FOLLOW_GROUP_in_group_by_clause726); 
            match(input,BY,FOLLOW_BY_in_group_by_clause728); 
            pushFollow(FOLLOW_grouping_element_list_in_group_by_clause730);
            grouping_element_list();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "group_by_clause"


    // $ANTLR start "grouping_element_list"
    // SQL99.g:161:1: grouping_element_list : grouping_element ( COMMA grouping_element )* ;
    public final void grouping_element_list() throws RecognitionException {
        try {
            // SQL99.g:162:3: ( grouping_element ( COMMA grouping_element )* )
            // SQL99.g:162:5: grouping_element ( COMMA grouping_element )*
            {
            pushFollow(FOLLOW_grouping_element_in_grouping_element_list743);
            grouping_element();

            state._fsp--;

            // SQL99.g:162:22: ( COMMA grouping_element )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // SQL99.g:162:23: COMMA grouping_element
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_grouping_element_list746); 
            	    pushFollow(FOLLOW_grouping_element_in_grouping_element_list748);
            	    grouping_element();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grouping_element_list"


    // $ANTLR start "grouping_element"
    // SQL99.g:165:1: grouping_element : ( grouping_column_reference | LPAREN grouping_column_reference_list RPAREN );
    public final void grouping_element() throws RecognitionException {
        try {
            // SQL99.g:166:3: ( grouping_column_reference | LPAREN grouping_column_reference_list RPAREN )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=STRING && LA24_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt24=1;
            }
            else if ( (LA24_0==LPAREN) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // SQL99.g:166:5: grouping_column_reference
                    {
                    pushFollow(FOLLOW_grouping_column_reference_in_grouping_element765);
                    grouping_column_reference();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:167:5: LPAREN grouping_column_reference_list RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_grouping_element771); 
                    pushFollow(FOLLOW_grouping_column_reference_list_in_grouping_element773);
                    grouping_column_reference_list();

                    state._fsp--;

                    match(input,RPAREN,FOLLOW_RPAREN_in_grouping_element775); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grouping_element"


    // $ANTLR start "grouping_column_reference"
    // SQL99.g:170:1: grouping_column_reference : column_reference ;
    public final void grouping_column_reference() throws RecognitionException {
        try {
            // SQL99.g:171:3: ( column_reference )
            // SQL99.g:171:5: column_reference
            {
            pushFollow(FOLLOW_column_reference_in_grouping_column_reference791);
            column_reference();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grouping_column_reference"


    // $ANTLR start "grouping_column_reference_list"
    // SQL99.g:174:1: grouping_column_reference_list : column_reference ( COMMA column_reference )* ;
    public final void grouping_column_reference_list() throws RecognitionException {
        try {
            // SQL99.g:175:3: ( column_reference ( COMMA column_reference )* )
            // SQL99.g:175:5: column_reference ( COMMA column_reference )*
            {
            pushFollow(FOLLOW_column_reference_in_grouping_column_reference_list806);
            column_reference();

            state._fsp--;

            // SQL99.g:175:22: ( COMMA column_reference )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==COMMA) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // SQL99.g:175:23: COMMA column_reference
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_grouping_column_reference_list809); 
            	    pushFollow(FOLLOW_column_reference_in_grouping_column_reference_list811);
            	    column_reference();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "grouping_column_reference_list"


    // $ANTLR start "joined_table"
    // SQL99.g:178:1: joined_table : JOIN ;
    public final void joined_table() throws RecognitionException {
        try {
            // SQL99.g:179:3: ( JOIN )
            // SQL99.g:179:5: JOIN
            {
            match(input,JOIN,FOLLOW_JOIN_in_joined_table828); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "joined_table"


    // $ANTLR start "table_primary"
    // SQL99.g:182:1: table_primary : table_name ( ( AS )? alias_name )? ;
    public final void table_primary() throws RecognitionException {
        try {
            // SQL99.g:183:3: ( table_name ( ( AS )? alias_name )? )
            // SQL99.g:183:5: table_name ( ( AS )? alias_name )?
            {
            pushFollow(FOLLOW_table_name_in_table_primary841);
            table_name();

            state._fsp--;

            // SQL99.g:183:16: ( ( AS )? alias_name )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==AS||(LA27_0>=STRING && LA27_0<=STRING_WITH_QUOTE_DOUBLE)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // SQL99.g:183:17: ( AS )? alias_name
                    {
                    // SQL99.g:183:17: ( AS )?
                    int alt26=2;
                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==AS) ) {
                        alt26=1;
                    }
                    switch (alt26) {
                        case 1 :
                            // SQL99.g:183:17: AS
                            {
                            match(input,AS,FOLLOW_AS_in_table_primary844); 

                            }
                            break;

                    }

                    pushFollow(FOLLOW_alias_name_in_table_primary847);
                    alias_name();

                    state._fsp--;


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_primary"


    // $ANTLR start "table_name"
    // SQL99.g:186:1: table_name : ( schema_name DOT )? table_identifier ;
    public final void table_name() throws RecognitionException {
        try {
            // SQL99.g:187:3: ( ( schema_name DOT )? table_identifier )
            // SQL99.g:187:5: ( schema_name DOT )? table_identifier
            {
            // SQL99.g:187:5: ( schema_name DOT )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==STRING) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==DOT) ) {
                    alt28=1;
                }
            }
            else if ( (LA28_0==STRING_WITH_QUOTE_DOUBLE) ) {
                int LA28_2 = input.LA(2);

                if ( (LA28_2==DOT) ) {
                    alt28=1;
                }
            }
            switch (alt28) {
                case 1 :
                    // SQL99.g:187:6: schema_name DOT
                    {
                    pushFollow(FOLLOW_schema_name_in_table_name865);
                    schema_name();

                    state._fsp--;

                    match(input,DOT,FOLLOW_DOT_in_table_name867); 

                    }
                    break;

            }

            pushFollow(FOLLOW_table_identifier_in_table_name871);
            table_identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_name"


    // $ANTLR start "alias_name"
    // SQL99.g:190:1: alias_name : identifier ;
    public final void alias_name() throws RecognitionException {
        try {
            // SQL99.g:191:3: ( identifier )
            // SQL99.g:191:5: identifier
            {
            pushFollow(FOLLOW_identifier_in_alias_name886);
            identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "alias_name"


    // $ANTLR start "table_identifier"
    // SQL99.g:194:1: table_identifier : identifier ;
    public final void table_identifier() throws RecognitionException {
        try {
            // SQL99.g:195:3: ( identifier )
            // SQL99.g:195:5: identifier
            {
            pushFollow(FOLLOW_identifier_in_table_identifier903);
            identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "table_identifier"


    // $ANTLR start "schema_name"
    // SQL99.g:198:1: schema_name : identifier ;
    public final void schema_name() throws RecognitionException {
        try {
            // SQL99.g:199:3: ( identifier )
            // SQL99.g:199:5: identifier
            {
            pushFollow(FOLLOW_identifier_in_schema_name918);
            identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "schema_name"


    // $ANTLR start "column_name"
    // SQL99.g:202:1: column_name : identifier ;
    public final void column_name() throws RecognitionException {
        try {
            // SQL99.g:203:3: ( identifier )
            // SQL99.g:203:5: identifier
            {
            pushFollow(FOLLOW_identifier_in_column_name935);
            identifier();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "column_name"


    // $ANTLR start "identifier"
    // SQL99.g:206:1: identifier : ( regular_identifier | delimited_identifier );
    public final void identifier() throws RecognitionException {
        try {
            // SQL99.g:207:3: ( regular_identifier | delimited_identifier )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==STRING) ) {
                alt29=1;
            }
            else if ( (LA29_0==STRING_WITH_QUOTE_DOUBLE) ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // SQL99.g:207:5: regular_identifier
                    {
                    pushFollow(FOLLOW_regular_identifier_in_identifier950);
                    regular_identifier();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SQL99.g:208:5: delimited_identifier
                    {
                    pushFollow(FOLLOW_delimited_identifier_in_identifier957);
                    delimited_identifier();

                    state._fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "identifier"


    // $ANTLR start "regular_identifier"
    // SQL99.g:211:1: regular_identifier : STRING ;
    public final void regular_identifier() throws RecognitionException {
        try {
            // SQL99.g:212:3: ( STRING )
            // SQL99.g:212:5: STRING
            {
            match(input,STRING,FOLLOW_STRING_in_regular_identifier970); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "regular_identifier"


    // $ANTLR start "delimited_identifier"
    // SQL99.g:215:1: delimited_identifier : STRING_WITH_QUOTE_DOUBLE ;
    public final void delimited_identifier() throws RecognitionException {
        try {
            // SQL99.g:216:3: ( STRING_WITH_QUOTE_DOUBLE )
            // SQL99.g:216:5: STRING_WITH_QUOTE_DOUBLE
            {
            match(input,STRING_WITH_QUOTE_DOUBLE,FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_delimited_identifier983); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "delimited_identifier"


    // $ANTLR start "value"
    // SQL99.g:219:1: value : ( TRUE | FALSE | NUMERIC | STRING_WITH_QUOTE );
    public final void value() throws RecognitionException {
        try {
            // SQL99.g:220:3: ( TRUE | FALSE | NUMERIC | STRING_WITH_QUOTE )
            // SQL99.g:
            {
            if ( (input.LA(1)>=TRUE && input.LA(1)<=STRING_WITH_QUOTE) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "equals_operator"
    // SQL99.g:226:1: equals_operator : EQUALS ;
    public final void equals_operator() throws RecognitionException {
        try {
            // SQL99.g:227:3: ( EQUALS )
            // SQL99.g:227:5: EQUALS
            {
            match(input,EQUALS,FOLLOW_EQUALS_in_equals_operator1028); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "equals_operator"


    // $ANTLR start "not_equals_operator"
    // SQL99.g:230:1: not_equals_operator : LESS GREATER ;
    public final void not_equals_operator() throws RecognitionException {
        try {
            // SQL99.g:231:3: ( LESS GREATER )
            // SQL99.g:231:5: LESS GREATER
            {
            match(input,LESS,FOLLOW_LESS_in_not_equals_operator1041); 
            match(input,GREATER,FOLLOW_GREATER_in_not_equals_operator1043); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "not_equals_operator"


    // $ANTLR start "less_than_operator"
    // SQL99.g:234:1: less_than_operator : LESS ;
    public final void less_than_operator() throws RecognitionException {
        try {
            // SQL99.g:235:3: ( LESS )
            // SQL99.g:235:5: LESS
            {
            match(input,LESS,FOLLOW_LESS_in_less_than_operator1058); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "less_than_operator"


    // $ANTLR start "greater_than_operator"
    // SQL99.g:238:1: greater_than_operator : GREATER ;
    public final void greater_than_operator() throws RecognitionException {
        try {
            // SQL99.g:239:3: ( GREATER )
            // SQL99.g:239:5: GREATER
            {
            match(input,GREATER,FOLLOW_GREATER_in_greater_than_operator1073); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "greater_than_operator"


    // $ANTLR start "less_than_or_equals_operator"
    // SQL99.g:242:1: less_than_or_equals_operator : LESS EQUALS ;
    public final void less_than_or_equals_operator() throws RecognitionException {
        try {
            // SQL99.g:243:3: ( LESS EQUALS )
            // SQL99.g:243:5: LESS EQUALS
            {
            match(input,LESS,FOLLOW_LESS_in_less_than_or_equals_operator1087); 
            match(input,EQUALS,FOLLOW_EQUALS_in_less_than_or_equals_operator1089); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "less_than_or_equals_operator"


    // $ANTLR start "greater_than_or_equals_operator"
    // SQL99.g:246:1: greater_than_or_equals_operator : GREATER EQUALS ;
    public final void greater_than_or_equals_operator() throws RecognitionException {
        try {
            // SQL99.g:247:3: ( GREATER EQUALS )
            // SQL99.g:247:5: GREATER EQUALS
            {
            match(input,GREATER,FOLLOW_GREATER_in_greater_than_or_equals_operator1103); 
            match(input,EQUALS,FOLLOW_EQUALS_in_greater_than_or_equals_operator1105); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "greater_than_or_equals_operator"

    // Delegated rules


    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA17_eotS =
        "\16\uffff";
    static final String DFA17_eofS =
        "\16\uffff";
    static final String DFA17_minS =
        "\1\40\2\11\4\40\2\46\2\uffff\3\40";
    static final String DFA17_maxS =
        "\1\41\2\50\1\41\1\45\1\50\1\46\2\50\2\uffff\3\45";
    static final String DFA17_acceptS =
        "\11\uffff\1\1\1\2\3\uffff";
    static final String DFA17_specialS =
        "\16\uffff}>";
    static final String[] DFA17_transitionS = {
            "\1\1\1\2",
            "\1\3\34\uffff\1\4\1\5\1\6",
            "\1\3\34\uffff\1\4\1\5\1\6",
            "\1\7\1\10",
            "\2\12\4\11",
            "\2\12\4\11\1\14\1\uffff\1\13",
            "\2\12\4\11\1\15",
            "\1\4\1\5\1\6",
            "\1\4\1\5\1\6",
            "",
            "",
            "\2\12\4\11",
            "\2\12\4\11",
            "\2\12\4\11"
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "122:1: comparison_predicate : ( column_reference comp_op value | column_reference comp_op column_reference );";
        }
    }
 

    public static final BitSet FOLLOW_query_in_parse30 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_parse32 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_query47 = new BitSet(new long[]{0x00000003001FC8E0L});
    public static final BitSet FOLLOW_set_quantifier_in_query49 = new BitSet(new long[]{0x00000003001FC8E0L});
    public static final BitSet FOLLOW_select_list_in_query52 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_table_expression_in_query54 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_set_quantifier0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASTERISK_in_select_list91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_sublist_in_select_list97 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_select_list100 = new BitSet(new long[]{0x00000003001FC8E0L});
    public static final BitSet FOLLOW_select_sublist_in_select_list102 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_qualified_asterisk_in_select_sublist119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_derived_column_in_select_sublist125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_identifier_in_qualified_asterisk140 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_DOT_in_qualified_asterisk142 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ASTERISK_in_qualified_asterisk144 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_expression_in_derived_column159 = new BitSet(new long[]{0x0000000300000402L});
    public static final BitSet FOLLOW_AS_in_derived_column162 = new BitSet(new long[]{0x0000000300000400L});
    public static final BitSet FOLLOW_alias_name_in_derived_column165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_reference_value_expression_in_value_expression183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collection_value_expression_in_value_expression189 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_reference_value_expression202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_identifier_in_column_reference216 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_DOT_in_column_reference218 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_column_name_in_column_reference222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_function_specification_in_collection_value_expression239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COUNT_in_set_function_specification252 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_set_function_specification254 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ASTERISK_in_set_function_specification256 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_set_function_specification258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_general_set_function_in_set_function_specification264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_function_op_in_general_set_function279 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LPAREN_in_general_set_function281 = new BitSet(new long[]{0x00000003001FC8E0L});
    public static final BitSet FOLLOW_set_quantifier_in_general_set_function284 = new BitSet(new long[]{0x00000003001FC8E0L});
    public static final BitSet FOLLOW_value_expression_in_general_set_function288 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_general_set_function290 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_set_function_op0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_from_clause_in_table_expression366 = new BitSet(new long[]{0x0000000020400002L});
    public static final BitSet FOLLOW_where_clause_in_table_expression369 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_group_by_clause_in_table_expression374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from_clause391 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_table_reference_list_in_from_clause393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_reference_in_table_reference_list410 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_table_reference_list413 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_table_reference_in_table_reference_list415 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_table_primary_in_table_reference432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_joined_table_in_table_reference439 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_where_clause452 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_search_condition_in_where_clause454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_value_expression_in_search_condition467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_boolean_term_in_boolean_value_expression482 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_set_in_boolean_value_expression485 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_boolean_term_in_boolean_value_expression491 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_predicate_in_boolean_term506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_predicate_in_predicate521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_null_predicate_in_predicate527 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_predicate_in_predicate533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_comparison_predicate548 = new BitSet(new long[]{0x000001C000000000L});
    public static final BitSet FOLLOW_comp_op_in_comparison_predicate550 = new BitSet(new long[]{0x0000003C00000000L});
    public static final BitSet FOLLOW_value_in_comparison_predicate552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_comparison_predicate558 = new BitSet(new long[]{0x000001C000000000L});
    public static final BitSet FOLLOW_comp_op_in_comparison_predicate560 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_column_reference_in_comparison_predicate562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_equals_operator_in_comp_op575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_not_equals_operator_in_comp_op581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_less_than_operator_in_comp_op587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_greater_than_operator_in_comp_op593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_less_than_or_equals_operator_in_comp_op599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_greater_than_or_equals_operator_in_comp_op605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_null_predicate618 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_IS_in_null_predicate620 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_NOT_in_null_predicate623 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_NULL_in_null_predicate627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_in_predicate640 = new BitSet(new long[]{0x0000000014000000L});
    public static final BitSet FOLLOW_NOT_in_in_predicate643 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_IN_in_in_predicate647 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_in_predicate_value_in_in_predicate649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_subquery_in_in_predicate_value664 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_in_predicate_value670 = new BitSet(new long[]{0x0000003C00000000L});
    public static final BitSet FOLLOW_value_list_in_in_predicate_value672 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_in_predicate_value674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_table_subquery687 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_query_in_table_subquery689 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_table_subquery691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_value_list706 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_value_list709 = new BitSet(new long[]{0x0000003C00000000L});
    public static final BitSet FOLLOW_value_in_value_list711 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_GROUP_in_group_by_clause726 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_BY_in_group_by_clause728 = new BitSet(new long[]{0x0000000300001000L});
    public static final BitSet FOLLOW_grouping_element_list_in_group_by_clause730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_grouping_element_in_grouping_element_list743 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_grouping_element_list746 = new BitSet(new long[]{0x0000000300001000L});
    public static final BitSet FOLLOW_grouping_element_in_grouping_element_list748 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_grouping_column_reference_in_grouping_element765 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_grouping_element771 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_grouping_column_reference_list_in_grouping_element773 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_grouping_element775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_grouping_column_reference791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_column_reference_in_grouping_column_reference_list806 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_grouping_column_reference_list809 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_column_reference_in_grouping_column_reference_list811 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_JOIN_in_joined_table828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_table_name_in_table_primary841 = new BitSet(new long[]{0x0000000300000402L});
    public static final BitSet FOLLOW_AS_in_table_primary844 = new BitSet(new long[]{0x0000000300000400L});
    public static final BitSet FOLLOW_alias_name_in_table_primary847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_schema_name_in_table_name865 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_DOT_in_table_name867 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_table_identifier_in_table_name871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_alias_name886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_table_identifier903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_schema_name918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_identifier_in_column_name935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_regular_identifier_in_identifier950 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_delimited_identifier_in_identifier957 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_regular_identifier970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_WITH_QUOTE_DOUBLE_in_delimited_identifier983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_value0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_equals_operator1028 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_not_equals_operator1041 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_GREATER_in_not_equals_operator1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_less_than_operator1058 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_greater_than_operator1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESS_in_less_than_or_equals_operator1087 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_EQUALS_in_less_than_or_equals_operator1089 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_greater_than_or_equals_operator1103 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_EQUALS_in_greater_than_or_equals_operator1105 = new BitSet(new long[]{0x0000000000000002L});

}