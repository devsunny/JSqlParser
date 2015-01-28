/* DbDateParser.java */
/* Generated By:JavaCC: Do not edit this line. DbDateParser.java */
package com.asksunny.sql.date;
import java.io.StringReader;

public class DbDateParser implements DbDateParserConstants {

    public static void main(String args[])
    {
       
    	String test  = "YYYY/mm/DD";
    	System.out.println(DateFormatConverter.toJavaDateFormat(test));
    	
    	try
        {
          String query = "YY-MM-DD";
            DbDateParser parser = new DbDateParser(new StringReader(query));
            java.util.List<DateFormatPart> dfps = parser.parseDateFormat();
            for (DateFormatPart dateFormatPart : dfps) {
                System.out.println(String.format("Tyep:%s:%s", dateFormatPart.getType(), dateFormatPart.toString()));
                        }
        }
        catch(Exception e)
        {
                e.printStackTrace();
        }
    }

  final public java.util.List<DateFormatPart> parseDateFormat() throws ParseException {java.util.List<DateFormatPart> dateparts = null;
        java.util.List<DateFormatPart> timeparts = null;
        DateFormatPart dtSep = null;
    if (jj_2_2(2)) {
      dateparts = date();
      if (jj_2_1(2)) {
        dtSep = dateSeparator();
        timeparts = time();
      } else {
        ;
      }
    } else if (jj_2_3(2)) {
      timeparts = time();
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
java.util.List<DateFormatPart> dtparts = null;
                if(dateparts!=null){
                        dtparts = dateparts;
                        if(timeparts!=null){
                                dtparts.add(dtSep);
                                dtparts.addAll(timeparts);
                        }
                }else{
                        dtparts = timeparts;
                }

                {if ("" != null) return dtparts;}
    throw new Error("Missing return statement in function");
  }

  final public java.util.List<DateFormatPart> date() throws ParseException {String tmg = null;
        java.util.List<DateFormatPart> parts = new java.util.ArrayList<DateFormatPart>();
        DateFormatPart dfp1 = null;
        DateFormatPart sep1 = null;
        DateFormatPart dfp2 = null;
        DateFormatPart sep2 = null;
        DateFormatPart dfp3 = null;
    if (jj_2_4(2)) {
      dfp1 = month();
      sep1 = dateSeparator();
      jj_consume_token(DAY);
dfp2 = new DateFormatPart(DateFormatPart.Type.DAY);
      sep2 = dateSeparator();
      dfp3 = year();
    } else if (jj_2_5(2)) {
      jj_consume_token(DAY);
dfp1 = new DateFormatPart(DateFormatPart.Type.DAY);
      sep1 = dateSeparator();
      dfp2 = month();
      sep2 = dateSeparator();
      dfp3 = year();
    } else if (jj_2_6(2)) {
      dfp1 = year();
      sep1 = dateSeparator();
      dfp2 = month();
      sep2 = dateSeparator();
      jj_consume_token(DAY);
dfp3 = new DateFormatPart(DateFormatPart.Type.DAY);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
if(dfp1!=null) parts.add(dfp1);
                if(sep1!=null) parts.add(sep1);
                if(dfp2!=null) parts.add(dfp2);
                if(sep2!=null) parts.add(sep2);
                if(dfp3!=null) parts.add(dfp3);

                {if ("" != null) return parts;}
    throw new Error("Missing return statement in function");
  }

  final public DateFormatPart year() throws ParseException {DateFormatPart year = null;
    if (jj_2_7(2)) {
      jj_consume_token(YEAR_YYYY);
year = new DateFormatPart(DateFormatPart.Type.YYYY);
    } else if (jj_2_8(2)) {
      jj_consume_token(YEAR_YY);
year = new DateFormatPart(DateFormatPart.Type.YY);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return year;}
    throw new Error("Missing return statement in function");
  }

  final public DateFormatPart month() throws ParseException {DateFormatPart month = null;
    if (jj_2_9(2)) {
      jj_consume_token(MONTH_MONTH);
month = new DateFormatPart(DateFormatPart.Type.MONTH_MONTH);
    } else if (jj_2_10(2)) {
      jj_consume_token(MONTH_MON);
month = new DateFormatPart(DateFormatPart.Type.MONTH_MON);
    } else if (jj_2_11(2)) {
      jj_consume_token(MON_OR_MIN);
month = new DateFormatPart(DateFormatPart.Type.MONTH_MM);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return month;}
    throw new Error("Missing return statement in function");
  }

  final public java.util.List<DateFormatPart> time() throws ParseException {Boolean am = null;
        String tmg = null;
        java.util.List<DateFormatPart> parts = new java.util.ArrayList<DateFormatPart>();
        DateFormatPart hh = null;
        DateFormatPart sep1 = null;
        DateFormatPart mm = null;
        DateFormatPart sep2 = null;
        DateFormatPart ss = null;
        DateFormatPart sep3 = null;
        DateFormatPart milli = null;
    jj_consume_token(HOUR);

hh = new DateFormatPart(DateFormatPart.Type.HOUR);
    sep1 = timeSeparator();
    if (jj_2_12(2)) {
      jj_consume_token(MIN);
    } else if (jj_2_13(2)) {
      jj_consume_token(MON_OR_MIN);
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
mm = new DateFormatPart(DateFormatPart.Type.MINUTE);
    if (jj_2_15(2)) {
      sep2 = timeSeparator();
      jj_consume_token(SECOND);
ss=new DateFormatPart(DateFormatPart.Type.SECOND);
      if (jj_2_14(2)) {
        sep3 = timeSeparator();
        jj_consume_token(MILLISECOND);
milli=new DateFormatPart(DateFormatPart.Type.MILLISECOND);
      } else {
        ;
      }
    } else {
      ;
    }
    if (jj_2_16(2)) {
      am = ampm();
    } else {
      ;
    }
if(hh!=null) parts.add(hh);
                if(sep1!=null) parts.add(sep1);
                if(mm!=null) parts.add(mm);
                if(sep2!=null) parts.add(sep2);
                if(ss!=null) parts.add(ss);
                if(sep3!=null) parts.add(sep3);
                if(milli!=null) parts.add(milli);
                if(am!=null) {
                        DateFormatPart apm = new DateFormatPart(DateFormatPart.Type.AMPM);
                        if(am){
                                apm.setValue("AM");
                        }else{
                                apm.setValue("PM");
                        }
                        parts.add(apm);
                }
                {if ("" != null) return parts;}
    throw new Error("Missing return statement in function");
  }

  final public Boolean ampm() throws ParseException {Boolean am = null;
    if (jj_2_17(2)) {
      jj_consume_token(AM);
am =Boolean.TRUE;
    } else if (jj_2_18(2)) {
      jj_consume_token(PM);
am =Boolean.FALSE;
    } else {
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return am;}
    throw new Error("Missing return statement in function");
  }

  final public DateFormatPart dateSeparator() throws ParseException {StringBuilder buf = new StringBuilder();
    label_1:
    while (true) {
      if (jj_2_19(2)) {
        ;
      } else {
        break label_1;
      }
      if (jj_2_20(2)) {
        jj_consume_token(HYPHEN);
buf.append(token.image);
      } else if (jj_2_21(2)) {
        jj_consume_token(UNDERSCORE);
buf.append(token.image);
      } else if (jj_2_22(2)) {
        jj_consume_token(COMMA);
buf.append(token.image);
      } else if (jj_2_23(2)) {
        jj_consume_token(SPACE);
buf.append(token.image);
      } else if (jj_2_24(2)) {
        jj_consume_token(SLASH);
buf.append(token.image);
      } else {
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
DateFormatPart dfp = new DateFormatPart(DateFormatPart.Type.SEPARATOR);
        dfp.setValue(buf.toString());
        {if ("" != null) return dfp;}
    throw new Error("Missing return statement in function");
  }

  final public DateFormatPart timeSeparator() throws ParseException {StringBuilder buf = new StringBuilder();
    label_2:
    while (true) {
      if (jj_2_25(2)) {
        ;
      } else {
        break label_2;
      }
      jj_consume_token(COLON);
buf.append(token.image);
    }
DateFormatPart dfp = new DateFormatPart(DateFormatPart.Type.SEPARATOR);
        dfp.setValue(buf.toString());
        {if ("" != null) return dfp;}
    throw new Error("Missing return statement in function");
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_2_11(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  private boolean jj_2_12(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  private boolean jj_2_13(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  private boolean jj_2_14(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  private boolean jj_2_15(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  private boolean jj_2_16(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  private boolean jj_2_17(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  private boolean jj_2_18(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  private boolean jj_2_19(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  private boolean jj_2_20(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  private boolean jj_2_21(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  private boolean jj_2_22(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  private boolean jj_2_23(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  private boolean jj_2_24(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  private boolean jj_2_25(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  private boolean jj_3R_4()
 {
    if (jj_scan_token(HOUR)) return true;
    if (jj_3R_8()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_12()) {
    jj_scanpos = xsp;
    if (jj_3_13()) return true;
    }
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_scan_token(DAY)) return true;
    if (jj_3R_3()) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_6()
 {
    if (jj_3R_7()) return true;
    if (jj_3R_3()) return true;
    if (jj_3R_6()) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_5()) return true;
    return false;
  }

  private boolean jj_3_24()
 {
    if (jj_scan_token(SLASH)) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    if (jj_3R_6()) return true;
    if (jj_3R_3()) return true;
    if (jj_scan_token(DAY)) return true;
    return false;
  }

  private boolean jj_3_23()
 {
    if (jj_scan_token(SPACE)) return true;
    return false;
  }

  private boolean jj_3_22()
 {
    if (jj_scan_token(COMMA)) return true;
    return false;
  }

  private boolean jj_3_21()
 {
    if (jj_scan_token(UNDERSCORE)) return true;
    return false;
  }

  private boolean jj_3_20()
 {
    if (jj_scan_token(HYPHEN)) return true;
    return false;
  }

  private boolean jj_3_19()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_20()) {
    jj_scanpos = xsp;
    if (jj_3_21()) {
    jj_scanpos = xsp;
    if (jj_3_22()) {
    jj_scanpos = xsp;
    if (jj_3_23()) {
    jj_scanpos = xsp;
    if (jj_3_24()) return true;
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_3()
 {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_19()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_17()
 {
    if (jj_scan_token(AM)) return true;
    return false;
  }

  private boolean jj_3R_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_17()) {
    jj_scanpos = xsp;
    if (jj_3_18()) return true;
    }
    return false;
  }

  private boolean jj_3R_5()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3_6()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_11()
 {
    if (jj_scan_token(MON_OR_MIN)) return true;
    return false;
  }

  private boolean jj_3_10()
 {
    if (jj_scan_token(MONTH_MON)) return true;
    return false;
  }

  private boolean jj_3_9()
 {
    if (jj_scan_token(MONTH_MONTH)) return true;
    return false;
  }

  private boolean jj_3R_6()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_9()) {
    jj_scanpos = xsp;
    if (jj_3_10()) {
    jj_scanpos = xsp;
    if (jj_3_11()) return true;
    }
    }
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_3()) return true;
    if (jj_3R_4()) return true;
    return false;
  }

  private boolean jj_3_16()
 {
    if (jj_3R_9()) return true;
    return false;
  }

  private boolean jj_3_18()
 {
    if (jj_scan_token(PM)) return true;
    return false;
  }

  private boolean jj_3_13()
 {
    if (jj_scan_token(MON_OR_MIN)) return true;
    return false;
  }

  private boolean jj_3_8()
 {
    if (jj_scan_token(YEAR_YY)) return true;
    return false;
  }

  private boolean jj_3_7()
 {
    if (jj_scan_token(YEAR_YYYY)) return true;
    return false;
  }

  private boolean jj_3_14()
 {
    if (jj_3R_8()) return true;
    if (jj_scan_token(MILLISECOND)) return true;
    return false;
  }

  private boolean jj_3R_7()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3_8()) return true;
    }
    return false;
  }

  private boolean jj_3_25()
 {
    if (jj_scan_token(COLON)) return true;
    return false;
  }

  private boolean jj_3_15()
 {
    if (jj_3R_8()) return true;
    if (jj_scan_token(SECOND)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_14()) jj_scanpos = xsp;
    return false;
  }

  private boolean jj_3R_8()
 {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_25()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_12()
 {
    if (jj_scan_token(MIN)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public DbDateParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[25];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public DbDateParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public DbDateParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new DbDateParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public DbDateParser(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new DbDateParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public DbDateParser(DbDateParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(DbDateParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[19];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 19; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 25; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
            case 16: jj_3_17(); break;
            case 17: jj_3_18(); break;
            case 18: jj_3_19(); break;
            case 19: jj_3_20(); break;
            case 20: jj_3_21(); break;
            case 21: jj_3_22(); break;
            case 22: jj_3_23(); break;
            case 23: jj_3_24(); break;
            case 24: jj_3_25(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
