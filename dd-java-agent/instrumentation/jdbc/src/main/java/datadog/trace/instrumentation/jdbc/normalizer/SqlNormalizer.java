/* Generated By:JavaCC: Do not edit this line. SqlNormalizer.java */
package datadog.trace.instrumentation.jdbc.normalizer;

import java.io.StringReader;

public class SqlNormalizer implements SqlNormalizerConstants {
  public static String normalize(String s) throws ParseException {
    SqlNormalizer parser = new SqlNormalizer(new StringReader(s));
    return parser.Input().toString();
  }

  public final StringBuffer Input() throws ParseException {
    final int LIMIT = 32 * 1024;
    StringBuffer sb = new StringBuffer();
    Token t;
    label_1:
    while (true) {
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case Id:
        case BasicNum:
        case HexNum:
        case QuotedStr:
        case DoubleQuotedStr:
        case Whitespace:
        case Other:;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
      }
      switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case Id:
          t = jj_consume_token(Id);
          sb.append(t.image);
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case BasicNum:
          t = jj_consume_token(BasicNum);
          sb.append('?');
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case HexNum:
          t = jj_consume_token(HexNum);
          sb.append('?');
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case QuotedStr:
          t = jj_consume_token(QuotedStr);
          sb.append('?');
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case DoubleQuotedStr:
          t = jj_consume_token(DoubleQuotedStr);
          sb.append('?');
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case Whitespace:
          t = jj_consume_token(Whitespace);
          sb.append(' ');
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        case Other:
          t = jj_consume_token(Other);
          sb.append(t.image);
          if (sb.length() > LIMIT) {
            {
              if (true) return sb;
            }
          }
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
      }
    }
    jj_consume_token(0);
    {
      if (true) return sb;
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public SqlNormalizerTokenManager token_source;

  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;

  private int jj_ntk;
  private int jj_gen;
  private final int[] jj_la1 = new int[2];
  private static int[] jj_la1_0;

  static {
    jj_la1_init_0();
  }

  private static void jj_la1_init_0() {
    jj_la1_0 =
        new int[] {
          0xfe, 0xfe,
        };
  }

  /** Constructor with InputStream. */
  public SqlNormalizer(java.io.InputStream stream) {
    this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public SqlNormalizer(java.io.InputStream stream, String encoding) {
    try {
      jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
    } catch (java.io.UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    token_source = new SqlNormalizerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
    ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try {
      jj_input_stream.ReInit(stream, encoding, 1, 1);
    } catch (java.io.UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public SqlNormalizer(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new SqlNormalizerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public SqlNormalizer(SqlNormalizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(SqlNormalizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  /** Get the next Token. */
  public final Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  /** Get the specific Token. */
  public final Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt = token.next) == null)
      return (jj_ntk = (token.next = token_source.getNextToken()).kind);
    else return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[8];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1 << j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 8; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[]) jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  public final void enable_tracing() {}

  /** Disable tracing. */
  public final void disable_tracing() {}
}
