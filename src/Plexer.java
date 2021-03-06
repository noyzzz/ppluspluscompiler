/* The following code was generated by JFlex 1.6.1 */

import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>Plexer.flex</tt>
 */
public class Plexer extends sym implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\17\1\15\1\0\1\17\1\14\22\0\1\17\1\44\1\66"+
    "\1\21\1\0\1\55\1\50\1\23\1\60\1\61\1\53\1\54\1\62"+
    "\1\22\1\2\1\20\1\1\11\1\1\63\1\16\1\45\1\43\1\46"+
    "\2\0\4\3\1\3\1\3\21\3\1\3\2\3\1\64\1\24\1\65"+
    "\1\52\1\3\1\0\1\11\1\25\1\33\1\35\1\7\1\10\1\26"+
    "\1\34\1\27\1\3\1\32\1\12\1\3\1\30\1\31\1\37\1\3"+
    "\1\5\1\13\1\4\1\6\1\42\1\41\1\36\1\3\1\40\1\56"+
    "\1\51\1\57\1\47\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff91\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\11\3\2\4\1\5\1\6\1\0"+
    "\1\7\1\0\10\3\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34"+
    "\1\35\1\36\1\0\17\3\4\0\1\37\2\0\4\3"+
    "\1\40\1\41\1\3\1\42\1\3\1\43\6\3\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1\54"+
    "\1\55\6\3\1\56\4\3\1\57\1\60\5\3\1\0"+
    "\1\61\1\0\1\61\1\62\1\0\4\3\1\63\1\3"+
    "\1\64\7\3\1\65\4\3\1\66\4\3\1\67\4\3"+
    "\2\0\1\70\2\3\1\71\1\72\2\3\1\73\2\3"+
    "\1\74\2\3\1\75\3\3\1\76\2\3\1\77\5\3"+
    "\1\100\1\101\1\102\2\3\1\103\2\3\1\104\1\105"+
    "\1\106\1\107\2\3\1\110\1\111\1\112\1\113\1\114"+
    "\2\3\1\115\1\3\1\116\1\3\1\117\1\120\1\121";

  private static int [] zzUnpackAction() {
    int [] result = new int[209];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb\0\245\0\245"+
    "\0\u0302\0\u0339\0\u0370\0\u03a7\0\u03de\0\u0415\0\u044c\0\u0483"+
    "\0\u04ba\0\u04f1\0\u0528\0\u055f\0\u0596\0\u05cd\0\u0604\0\u063b"+
    "\0\245\0\245\0\245\0\245\0\245\0\u0672\0\245\0\245"+
    "\0\245\0\245\0\245\0\245\0\245\0\245\0\245\0\245"+
    "\0\u06a9\0\u06e0\0\245\0\u0717\0\u074e\0\u0785\0\u07bc\0\u07f3"+
    "\0\u082a\0\u0861\0\u0898\0\u08cf\0\u0906\0\u093d\0\u0974\0\u09ab"+
    "\0\u09e2\0\u0a19\0\u0a50\0\u0a87\0\u0abe\0\u0af5\0\u0b2c\0\245"+
    "\0\u0b63\0\u0b9a\0\u0bd1\0\u0c08\0\u0c3f\0\u0c76\0\334\0\u0cad"+
    "\0\u0ce4\0\334\0\u0d1b\0\334\0\u0d52\0\u0d89\0\u0dc0\0\u0df7"+
    "\0\u0e2e\0\u0e65\0\245\0\245\0\245\0\245\0\245\0\245"+
    "\0\245\0\245\0\245\0\u0717\0\u0e9c\0\u0ed3\0\u0f0a\0\u0f41"+
    "\0\u0f78\0\u0faf\0\334\0\u0fe6\0\u101d\0\u1054\0\u108b\0\u10c2"+
    "\0\334\0\u10f9\0\u1130\0\u1167\0\u119e\0\u11d5\0\u120c\0\u1243"+
    "\0\u127a\0\245\0\245\0\u12b1\0\u12e8\0\u131f\0\u1356\0\u138d"+
    "\0\334\0\u13c4\0\334\0\u13fb\0\u1432\0\u1469\0\u14a0\0\u14d7"+
    "\0\u150e\0\u1545\0\334\0\u157c\0\u15b3\0\u15ea\0\u1621\0\334"+
    "\0\u1658\0\u168f\0\u16c6\0\u16fd\0\334\0\u1734\0\u176b\0\u17a2"+
    "\0\u17d9\0\u1243\0\u1810\0\245\0\u1847\0\u187e\0\334\0\334"+
    "\0\u18b5\0\u18ec\0\334\0\u1923\0\u195a\0\334\0\u1991\0\u19c8"+
    "\0\334\0\u19ff\0\u1a36\0\u1a6d\0\334\0\u1aa4\0\u1adb\0\334"+
    "\0\u1b12\0\u1b49\0\u1b80\0\u1bb7\0\u1bee\0\334\0\334\0\334"+
    "\0\u1c25\0\u1c5c\0\334\0\u1c93\0\u1cca\0\334\0\334\0\334"+
    "\0\334\0\u1d01\0\u1d38\0\334\0\334\0\334\0\334\0\334"+
    "\0\u1d6f\0\u1da6\0\334\0\u1ddd\0\334\0\u1e14\0\334\0\334"+
    "\0\334";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[209];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\17"+
    "\1\21\1\22\1\23\1\24\1\0\1\25\1\26\1\27"+
    "\1\30\1\31\1\5\1\32\1\5\1\33\4\5\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1\54"+
    "\1\55\1\56\1\57\1\60\14\61\2\0\6\61\1\62"+
    "\41\61\1\63\1\0\1\3\1\64\154\0\1\5\1\0"+
    "\11\5\11\0\16\5\25\0\1\5\1\0\2\5\1\65"+
    "\6\5\11\0\16\5\25\0\1\5\1\0\4\5\1\66"+
    "\4\5\11\0\16\5\25\0\1\5\1\0\11\5\11\0"+
    "\3\5\1\67\12\5\25\0\1\5\1\0\7\5\1\70"+
    "\1\5\11\0\3\5\1\71\5\5\1\72\4\5\25\0"+
    "\1\5\1\0\3\5\1\73\2\5\1\74\1\75\1\5"+
    "\11\0\4\5\1\76\11\5\25\0\1\5\1\0\11\5"+
    "\11\0\3\5\1\77\12\5\25\0\1\5\1\0\11\5"+
    "\11\0\4\5\1\100\11\5\25\0\1\5\1\0\1\5"+
    "\1\101\7\5\11\0\2\5\1\102\11\5\1\103\1\5"+
    "\41\0\1\104\71\0\1\105\1\106\66\0\1\107\67\0"+
    "\1\110\44\0\24\111\1\112\42\111\1\0\1\5\1\0"+
    "\2\5\1\113\1\5\1\114\4\5\11\0\4\5\1\115"+
    "\11\5\25\0\1\5\1\0\11\5\11\0\4\5\1\116"+
    "\11\5\25\0\1\5\1\0\5\5\1\117\3\5\11\0"+
    "\3\5\1\120\12\5\25\0\1\5\1\0\11\5\11\0"+
    "\4\5\1\121\11\5\25\0\1\5\1\0\2\5\1\122"+
    "\1\123\1\5\1\124\3\5\11\0\16\5\25\0\1\5"+
    "\1\0\6\5\1\125\2\5\11\0\4\5\1\126\2\5"+
    "\1\127\6\5\25\0\1\5\1\0\4\5\1\130\4\5"+
    "\11\0\4\5\1\131\11\5\25\0\1\5\1\0\11\5"+
    "\11\0\4\5\1\132\11\5\67\0\1\133\66\0\1\134"+
    "\66\0\1\135\66\0\1\136\77\0\1\137\12\0\14\61"+
    "\2\0\6\61\1\0\41\61\5\0\1\140\1\141\22\0"+
    "\1\142\35\0\1\143\1\0\1\144\66\0\1\5\1\0"+
    "\3\5\1\145\5\5\11\0\16\5\25\0\1\5\1\0"+
    "\1\5\1\146\7\5\11\0\6\5\1\147\3\5\1\150"+
    "\3\5\25\0\1\5\1\0\1\5\1\151\7\5\11\0"+
    "\16\5\25\0\1\5\1\0\10\5\1\152\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\10\5\1\153\5\5"+
    "\25\0\1\5\1\0\1\5\1\154\7\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\3\5\1\155\12\5"+
    "\25\0\1\5\1\0\7\5\1\156\1\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\4\5\1\157\11\5"+
    "\25\0\1\5\1\0\2\5\1\160\6\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\10\5\1\161\5\5"+
    "\25\0\1\5\1\0\11\5\11\0\3\5\1\162\12\5"+
    "\25\0\1\5\1\0\2\5\1\163\3\5\1\164\2\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\13\5"+
    "\1\165\2\5\25\0\1\5\1\0\11\5\11\0\2\5"+
    "\1\166\13\5\42\0\1\17\50\0\14\105\1\167\1\170"+
    "\51\105\21\106\1\171\45\106\14\107\1\172\52\107\23\0"+
    "\1\173\43\0\67\174\1\0\1\5\1\0\4\5\1\175"+
    "\4\5\11\0\16\5\25\0\1\5\1\0\11\5\11\0"+
    "\1\5\1\176\14\5\25\0\1\5\1\0\11\5\11\0"+
    "\4\5\1\177\11\5\25\0\1\5\1\0\1\5\1\200"+
    "\7\5\11\0\16\5\25\0\1\5\1\0\1\5\1\201"+
    "\7\5\11\0\12\5\1\202\3\5\25\0\1\5\1\0"+
    "\1\5\1\203\7\5\11\0\16\5\25\0\1\5\1\0"+
    "\1\5\1\204\7\5\11\0\16\5\25\0\1\5\1\0"+
    "\10\5\1\205\11\0\16\5\25\0\1\5\1\0\11\5"+
    "\11\0\3\5\1\206\12\5\25\0\1\5\1\0\6\5"+
    "\1\207\2\5\11\0\16\5\25\0\1\5\1\0\5\5"+
    "\1\210\3\5\11\0\16\5\25\0\1\5\1\0\3\5"+
    "\1\211\5\5\11\0\16\5\25\0\1\5\1\0\11\5"+
    "\11\0\2\5\1\212\13\5\25\0\1\5\1\0\4\5"+
    "\1\213\4\5\11\0\16\5\25\0\1\5\1\0\3\5"+
    "\1\214\5\5\11\0\16\5\25\0\1\5\1\0\11\5"+
    "\11\0\4\5\1\215\11\5\25\0\1\5\1\0\4\5"+
    "\1\216\4\5\11\0\16\5\25\0\1\5\1\0\11\5"+
    "\11\0\2\5\1\217\13\5\25\0\1\5\1\0\4\5"+
    "\1\220\4\5\11\0\16\5\25\0\1\5\1\0\4\5"+
    "\1\221\4\5\11\0\16\5\25\0\1\5\1\0\11\5"+
    "\11\0\6\5\1\222\7\5\25\0\1\5\1\0\10\5"+
    "\1\145\11\0\16\5\25\0\1\5\1\0\6\5\1\223"+
    "\2\5\11\0\16\5\25\0\1\5\1\0\4\5\1\224"+
    "\4\5\11\0\16\5\25\0\1\5\1\0\11\5\11\0"+
    "\1\5\1\225\14\5\25\0\1\5\1\0\11\5\11\0"+
    "\2\5\1\226\13\5\25\0\1\5\1\0\1\5\1\227"+
    "\7\5\11\0\16\5\25\0\1\5\1\0\4\5\1\230"+
    "\4\5\11\0\16\5\25\0\1\5\1\0\1\5\1\231"+
    "\7\5\11\0\16\5\24\0\15\167\1\172\51\167\14\232"+
    "\1\233\52\232\20\106\1\172\1\171\45\106\23\0\1\234"+
    "\44\0\1\5\1\0\6\5\1\235\2\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\2\5\1\236\13\5"+
    "\25\0\1\5\1\0\7\5\1\237\1\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\4\5\1\240\11\5"+
    "\25\0\1\5\1\0\3\5\1\241\5\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\12\5\1\242\3\5"+
    "\25\0\1\5\1\0\4\5\1\243\4\5\11\0\16\5"+
    "\25\0\1\5\1\0\1\5\1\244\6\5\1\245\11\0"+
    "\16\5\25\0\1\5\1\0\2\5\1\246\6\5\11\0"+
    "\16\5\25\0\1\5\1\0\6\5\1\247\2\5\11\0"+
    "\16\5\25\0\1\5\1\0\11\5\11\0\1\250\15\5"+
    "\25\0\1\5\1\0\11\5\11\0\10\5\1\251\5\5"+
    "\25\0\1\5\1\0\2\5\1\252\6\5\11\0\16\5"+
    "\25\0\1\5\1\0\2\5\1\253\6\5\11\0\16\5"+
    "\25\0\1\5\1\0\6\5\1\254\2\5\11\0\16\5"+
    "\25\0\1\5\1\0\7\5\1\255\1\5\11\0\16\5"+
    "\25\0\1\5\1\0\2\5\1\256\6\5\11\0\16\5"+
    "\25\0\1\5\1\0\1\5\1\257\7\5\11\0\16\5"+
    "\25\0\1\5\1\0\1\5\1\260\7\5\11\0\16\5"+
    "\25\0\1\5\1\0\6\5\1\261\2\5\11\0\16\5"+
    "\25\0\1\5\1\0\11\5\11\0\3\5\1\262\12\5"+
    "\25\0\1\5\1\0\11\5\11\0\2\5\1\263\13\5"+
    "\25\0\1\5\1\0\11\5\11\0\4\5\1\264\11\5"+
    "\25\0\1\5\1\0\11\5\11\0\6\5\1\265\7\5"+
    "\41\0\1\172\52\0\1\5\1\0\11\5\11\0\5\5"+
    "\1\266\10\5\25\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\267\12\5\25\0\1\5\1\0\1\5\1\270\7\5"+
    "\11\0\16\5\25\0\1\5\1\0\3\5\1\271\5\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\2\5"+
    "\1\272\13\5\25\0\1\5\1\0\1\5\1\273\7\5"+
    "\11\0\16\5\25\0\1\5\1\0\3\5\1\274\5\5"+
    "\11\0\16\5\25\0\1\5\1\0\7\5\1\275\1\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\276\12\5\25\0\1\5\1\0\11\5\11\0\10\5"+
    "\1\277\5\5\25\0\1\5\1\0\1\5\1\300\7\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\301\12\5\25\0\1\5\1\0\11\5\11\0\2\5"+
    "\1\302\13\5\25\0\1\5\1\0\11\5\11\0\6\5"+
    "\1\303\7\5\25\0\1\5\1\0\11\5\11\0\1\5"+
    "\1\304\14\5\25\0\1\5\1\0\11\5\11\0\6\5"+
    "\1\305\7\5\25\0\1\5\1\0\5\5\1\306\3\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\7\5"+
    "\1\307\6\5\25\0\1\5\1\0\1\5\1\310\7\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\311\12\5\25\0\1\5\1\0\7\5\1\312\1\5"+
    "\11\0\16\5\25\0\1\5\1\0\4\5\1\313\4\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\4\5"+
    "\1\314\11\5\25\0\1\5\1\0\11\5\11\0\7\5"+
    "\1\315\6\5\25\0\1\5\1\0\3\5\1\316\5\5"+
    "\11\0\16\5\25\0\1\5\1\0\1\5\1\317\7\5"+
    "\11\0\16\5\25\0\1\5\1\0\11\5\11\0\3\5"+
    "\1\320\12\5\25\0\1\5\1\0\4\5\1\321\4\5"+
    "\11\0\16\5\24\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7755];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\1\1\11\12\1\2\11\1\1\1\0\1\1"+
    "\1\0\14\1\5\11\1\1\12\11\2\1\1\11\1\0"+
    "\17\1\4\0\1\11\2\0\20\1\11\11\23\1\1\0"+
    "\1\1\1\0\2\11\1\0\35\1\2\0\1\11\65\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[209];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
        StringBuffer string = new StringBuffer();
        public Plexer(java.io.Reader in, ComplexSymbolFactory sf){
    	this(in);
    	symbolFactory = sf;
        }
        ComplexSymbolFactory symbolFactory;

      public Symbol symbol(String name, int sym) {
          return symbolFactory.newSymbol(name, sym, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
      }

      public Symbol symbol(String name, int sym, Object val) {
          Location left = new Location(yyline+1,yycolumn+1,yychar);
          Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
          return symbolFactory.newSymbol(name, sym, left, right,val);
      }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Plexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 180) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {      return symbolFactory.newSymbol("EOF", EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return symbol("intconst",INT_CONST, new Integer(Integer.parseInt(yytext())));
            }
          case 82: break;
          case 2: 
            { return symbol("dot",DOT);
            }
          case 83: break;
          case 3: 
            { return symbol("Identifier",ID, yytext());
            }
          case 84: break;
          case 4: 
            { /* ignore */
            }
          case 85: break;
          case 5: 
            { return symbol("semicolon", SEMICOLON);
            }
          case 86: break;
          case 6: 
            { return symbol("slash", SLASH);
            }
          case 87: break;
          case 7: 
            { return symbol("minus", MINUS);
            }
          case 88: break;
          case 8: 
            { return symbol("equal", EQUAL);
            }
          case 89: break;
          case 9: 
            { return symbol("exclam", EXCLAM);
            }
          case 90: break;
          case 10: 
            { return symbol("less", LESS);
            }
          case 91: break;
          case 11: 
            { return symbol("greater", GREATER);
            }
          case 92: break;
          case 12: 
            { return symbol("tilde", TILDE);
            }
          case 93: break;
          case 13: 
            { return symbol("ampersand", AMPERSAND);
            }
          case 94: break;
          case 14: 
            { return symbol("pipe", PIPE);
            }
          case 95: break;
          case 15: 
            { return symbol("caret", CARET);
            }
          case 96: break;
          case 16: 
            { return symbol("times", TIMES);
            }
          case 97: break;
          case 17: 
            { return symbol("plus", PLUS);
            }
          case 98: break;
          case 18: 
            { return symbol("mod", MOD);
            }
          case 99: break;
          case 19: 
            { return symbol("lbrace", LBRACE);
            }
          case 100: break;
          case 20: 
            { return symbol("rbrace", RBRACE);
            }
          case 101: break;
          case 21: 
            { return symbol("lpar", LPAR);
            }
          case 102: break;
          case 22: 
            { return symbol("rpar", RPAR);
            }
          case 103: break;
          case 23: 
            { return symbol("comma", COMMA);
            }
          case 104: break;
          case 24: 
            { return symbol("colon", COLON);
            }
          case 105: break;
          case 25: 
            { return symbol("lbrac", LBRAC);
            }
          case 106: break;
          case 26: 
            { return symbol("rbrac", RBRAC);
            }
          case 107: break;
          case 27: 
            { string.setLength(0); yybegin(STRING);
            }
          case 108: break;
          case 28: 
            { string.append( yytext() );
            }
          case 109: break;
          case 29: 
            { string.append('\\');
            }
          case 110: break;
          case 30: 
            { yybegin(YYINITIAL);
      return symbol("StringConst",STRING_CONST,string.toString());
            }
          case 111: break;
          case 31: 
            { return symbol("minusminus", MINUSMINUS);
            }
          case 112: break;
          case 32: 
            { return symbol("if",IF);
            }
          case 113: break;
          case 33: 
            { return symbol("in",IN);
            }
          case 114: break;
          case 34: 
            { return symbol("or", OR);
            }
          case 115: break;
          case 35: 
            { return symbol("of",OF);
            }
          case 116: break;
          case 36: 
            { return symbol("isequal", ISEQUAL);
            }
          case 117: break;
          case 37: 
            { return symbol("notEqual", NOTEQUAL);
            }
          case 118: break;
          case 38: 
            { return symbol("leq", LEQ);
            }
          case 119: break;
          case 39: 
            { return symbol("geq", GEQ);
            }
          case 120: break;
          case 40: 
            { return symbol("plusplus", PLUSPLUS);
            }
          case 121: break;
          case 41: 
            { string.append('\t');
            }
          case 122: break;
          case 42: 
            { string.append('\r');
            }
          case 123: break;
          case 43: 
            { string.append('\n');
            }
          case 124: break;
          case 44: 
            { string.append('\"');
            }
          case 125: break;
          case 45: 
            { return symbol("realconst",REAL_CONST, new Double(Double.parseDouble(yytext())));
            }
          case 126: break;
          case 46: 
            { return symbol("end",END);
            }
          case 127: break;
          case 47: 
            { return symbol("for",FOR);
            }
          case 128: break;
          case 48: 
            { return symbol("and", AND);
            }
          case 129: break;
          case 49: 
            { /**/
            }
          case 130: break;
          case 50: 
            { return symbol("character",CHAR_CONST,new Character(yytext().charAt(1)));
            }
          case 131: break;
          case 51: 
            { return symbol("int",INT, new Integer( INT_TYPE ));
            }
          case 132: break;
          case 52: 
            { return symbol("not", NOT);
            }
          case 133: break;
          case 53: 
            { return symbol("boolconst",BOOL_CONST, new Boolean(Boolean.parseBoolean(yytext())));
            }
          case 134: break;
          case 54: 
            { return symbol("else",ELSE);
            }
          case 135: break;
          case 55: 
            { return symbol("long",LONG);
            }
          case 136: break;
          case 56: 
            { return symbol("character",CHAR_CONST,new Character(yytext().charAt(2)));
            }
          case 137: break;
          case 57: 
            { return symbol("bool",BOOL);
            }
          case 138: break;
          case 58: 
            { return symbol("goto",GOTO);
            }
          case 139: break;
          case 59: 
            { return symbol("case",CASE);
            }
          case 140: break;
          case 60: 
            { return symbol("char",CHAR);
            }
          case 141: break;
          case 61: 
            { return symbol("void",VOID);
            }
          case 142: break;
          case 62: 
            { return symbol("until",UNTIL);
            }
          case 143: break;
          case 63: 
            { return symbol("float",FLOAT);
            }
          case 144: break;
          case 64: 
            { return symbol("break",BREAK);
            }
          case 145: break;
          case 65: 
            { return symbol("begin",BEGIN);
            }
          case 146: break;
          case 66: 
            { return symbol("input",INPUT);
            }
          case 147: break;
          case 67: 
            { return symbol("const",CONST);
            }
          case 148: break;
          case 68: 
            { return symbol("return",RETURN);
            }
          case 149: break;
          case 69: 
            { return symbol("record",RECORD);
            }
          case 150: break;
          case 70: 
            { return symbol("repeat",REPEAT);
            }
          case 151: break;
          case 71: 
            { return symbol("extern",EXTERN);
            }
          case 152: break;
          case 72: 
            { return symbol("string",STRING);
            }
          case 153: break;
          case 73: 
            { return symbol("static",STATIC);
            }
          case 154: break;
          case 74: 
            { return symbol("sizeof",SIZEOF);
            }
          case 155: break;
          case 75: 
            { return symbol("switch",SWITCH);
            }
          case 156: break;
          case 76: 
            { return symbol("output",OUTPUT);
            }
          case 157: break;
          case 77: 
            { return symbol("double",DOUBLE);
            }
          case 158: break;
          case 78: 
            { return symbol("foreach",FOREACH);
            }
          case 159: break;
          case 79: 
            { return symbol("default",DEFAULT);
            }
          case 160: break;
          case 80: 
            { return symbol("function",FUNCTION);
            }
          case 161: break;
          case 81: 
            { return symbol("continue",CONTINUE);
            }
          case 162: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
