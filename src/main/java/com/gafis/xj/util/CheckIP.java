package com.gafis.xj.util;

/**
 * IP格式校验
 * @author sun
 *
 */
public final class CheckIP {
	
	 private static boolean isValidValue(String pSegment)
	  {
	    String segment = pSegment;

	    if (('0' == segment.charAt(0)) && (segment.length() > 1))
	    {
	      return false;
	    }

	    try
	    {
	      int ipvalue = Integer.parseInt(segment);

	      if ((ipvalue < 0) || (ipvalue > 255))
	      {
	        return false;
	      }

	      return true;
	    }
	    catch (Exception ex)
	    {
	    }
	    return false;
	  }

	  public static boolean checkIPValid(String pIP)
	  {
	    char ch = '.';

	    String ip = pIP;
	    String[] segment = new String[4];

	    int begin = 0; int end = 0; int num = 0;

	    for (int i = 0; i < ip.length(); i++)
	    {
	      if (ch == ip.charAt(i))
	      {
	        if (begin == i)
	        {
	          return false;
	        }
	        end = i;
	        char[] temp = new char[end - begin];
	        ip.getChars(begin, end, temp, 0);

	        if (num < 3)
	        {
	          segment[num] = new String(temp);
	          num++;
	          begin = i + 1;
	        }
	        else
	        {
	          return false;
	        }
	      }
	    }

	    if (num < 3)
	    {
	      return false;
	    }

	    if (ip.length() == begin)
	    {
	      return false;
	    }

	    int last = ip.length();
	    char[] temp = new char[last - begin];
	    ip.getChars(begin, last, temp, 0);
	    segment[3] = new String(temp);

	    for (int i = 0; i < segment.length; i++)
	    {
	      boolean b = isValidValue(segment[i]);
	      if (!b)
	      {
	        return false;
	      }
	    }

	    return true;
	  }
}
