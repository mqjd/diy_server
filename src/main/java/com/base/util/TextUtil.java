package com.base.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.base.framework.exception.BusinessException;
import com.base.framework.exception.base.BaseExceptionDesc;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class TextUtil {

	public static boolean isNotEmpty(String text){
		
		return !isEmpty(text);
	}
	
	public static boolean isEmpty(String text){
		
		if(text == null){
			return true;
		}
		
		text = text.trim().toLowerCase().replaceAll("'|\"", "");
		
		return "".equals(text) || "null".equals(text);
	}
	
	public static String humbToUnderline(String varName){
		
		StringBuilder words = new StringBuilder();
		for(int i = 0; i < varName.length(); i ++){
			
			char c = varName.charAt(i);
			if(Character.isUpperCase(c)){
				
				if(i > 0){
					words.append("_");
				}
				words.append(c);
			}else{
				
				words.append(c);
			}
		}
		return words.toString().toUpperCase();
	}

	public static String underlineToHumb(String varName){
		
		if(varName != null){
			
			varName = varName.toLowerCase();
			
			String newName = "";
			for(String part : varName.split("_")){
				newName += part.substring(0, 1).toUpperCase() + part.substring(1);
			}
			newName = newName.substring(0, 1).toLowerCase() + newName.substring(1);
			
			return newName;
		}
		
		return null;
	}
	
	public static String getFullPinYin(String str) {
		String result = "";
		if (isNotEmpty(str)) {
			try {
				result = PinyinHelper.convertToPinyinString(str, "",PinyinFormat.WITHOUT_TONE);
			} catch (PinyinException e) {
				throw new BusinessException(new BaseExceptionDesc("1", "根据汉字："+str+"获取拼音异常",e));
			}
		}
		return result;
		
	}
	
	public static String getShortPinYin(String str) {
		String result = "";
		if (isNotEmpty(str)) {
			try {
				result = PinyinHelper.getShortPinyin(str);
			} catch (PinyinException e) {
				throw new BusinessException(new BaseExceptionDesc("1", "根据汉字："+str+"获取拼音异常",e));
			}
		}
		return result;
		
	}
	
	public static Set<String> getFullPinYinAll(String str) {
		Set<String> result = null;
		if (isNotEmpty(str)) {
			Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
			Matcher matcher = pattern.matcher(str);
			String str_ = matcher.replaceAll("").replace(" ", "");
			for (int i = 0; i < str_.length(); i++) {
				char charAt = str_.charAt(i);
				if (PinyinHelper.hasMultiPinyin(charAt)) {
					String[] pinyinArray = PinyinHelper.convertToPinyinArray(charAt, PinyinFormat.WITHOUT_TONE);
					result = addFullPinYin(result,pinyinArray);
				}else{
					try {
						String[] pinyinArray = PinyinHelper.convertToPinyinArray(charAt, PinyinFormat.WITHOUT_TONE);
						String pinyin = pinyinArray!=null&&pinyinArray.length>0?pinyinArray[0]:String.valueOf(charAt);
						result = addFullPinYin(result,pinyin);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		return result;
	}
	
	private static Set<String> addFullPinYin(Set<String> array,String pinyin) {
		Set<String> result = new HashSet<String>();
		if (array == null) {
			result.add(pinyin);
		}else {
			Iterator<String> iterator = array.iterator();
			while (iterator.hasNext()) {
				result.add(iterator.next()+pinyin);
				
			}
		}
		return result;
	}
	private static Set<String> addFullPinYin(Set<String> array,String[] pinyins) {
		
		Set<String> result = new HashSet<String>();
		if (array == null) {
			for (int j = 0; j < pinyins.length; j++) {
				result.add(pinyins[j]);
			}
		}else {
			Iterator<String> iterator = array.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				for (int j = 0; j < pinyins.length; j++) {
					result.add(next+pinyins[j]);
				}
			}
		}
		return result;
	}
	
	private static Set<String> addShortPinYin(Set<String> array,String pinyin) {
		Set<String> result = new HashSet<String>();
		if (array == null) {
			result.add(pinyin.substring(0,1));
		}else {
			Iterator<String> iterator = array.iterator();
			while (iterator.hasNext()) {
				result.add(iterator.next()+pinyin.substring(0,1));
			}
		}
		return result;
	}
	private static Set<String> addShortPinYin(Set<String> array,String[] pinyins) {
		Set<String> result = new HashSet<String>();
		if (array == null) {
			for (int j = 0; j < pinyins.length; j++) {
				result.add(pinyins[j].substring(0, 1));
			}
		}else {
			Iterator<String> iterator = array.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				for (int j = 0; j < pinyins.length; j++) {
					result.add(next+pinyins[j].substring(0,1));
				}
			}
		}
		return result;
	}
	
	public static Set<String> getShortPinYinAll(String str) {
		Set<String> result = null;
		if (isNotEmpty(str)) {
			Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");
			Matcher matcher = pattern.matcher(str);
			String str_ = matcher.replaceAll("").replace(" ", "");
			for (int i = 0; i < str_.length(); i++) {
				char charAt = str_.charAt(i);
				if (PinyinHelper.hasMultiPinyin(charAt)) {
					String[] pinyinArray = PinyinHelper.convertToPinyinArray(charAt, PinyinFormat.WITHOUT_TONE);
					result = addShortPinYin(result,pinyinArray);
				}else{
					try {
						String[] pinyinArray = PinyinHelper.convertToPinyinArray(charAt, PinyinFormat.WITHOUT_TONE);
						String pinyin = pinyinArray!=null&&pinyinArray.length>0?pinyinArray[0]:String.valueOf(charAt);
						result = addShortPinYin(result,pinyin);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		return result;
	}
	
	public static String List2String(Set<String> set) {
		String result = "";
		if (set != null) {
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String next = iterator.next();
				result+= next+",";
			}
		}
		return result.substring(0,result.length()-1);
	}
	
}
