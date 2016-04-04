package charAlgorithm.util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 辅助运算类
 * @author Kajelas
 *
 */
public class CalculateUtil {
	
	/**
	 * 统计字集charList中的部件能在totalCharList范围内构成哪些字
	 * @param totalCharList 字集范围list（汉字以部件代码的array表示）
	 * @param charList 待统计字集list（汉字以部件代码的array表示）
	 * @return List<String[]> 统计出的字构成的字集list（汉字以部件代码的array表示）
	 */
	public static List<String[]> calTotalCharNum(List<String[]> totalCharList, List<String[]> charList) {
		Set<String> partSet = new HashSet<String>();
		for (String[] singleChar : charList) {
			for (String part : singleChar) {
				partSet.add(part);
			}
		}
		
		return getTotalChar(totalCharList, partSet);
	}
	
	/**
	 * 统计部件集partsArr中的部件能在totalCharList范围内构成哪些字
	 * @param totalCharList 字集范围list（汉字以部件代码的array表示）
	 * @param partsArr 部件Array
	 * @return List<String[]> 统计出的字构成的字集list（汉字以部件代码的array表示）
	 */
	public static List<String[]> calTotalCharNum(List<String[]> totalCharList, String[] partsArr) {
		List<String> partList = Arrays.asList(partsArr);		
		return getTotalChar(totalCharList, partList);
	}
	
	//统计partCollection中的部件能够在totalCharList范围内构成哪些字
	private static List<String[]> getTotalChar(List<String[]> totalCharList, Collection<String> partCollection) {
		List<String[]> constructedCharList = new ArrayList<String[]>();
		for (String[] singleChar : totalCharList) {
			int count = 0;
			for (String part : singleChar) {
				if (partCollection.contains(part))
					count++;
			}
			if (count == singleChar.length) {
				constructedCharList.add(singleChar);
			}
		}
		
		return constructedCharList;
	}
	
	/**
	 * 计算charList中的字总的使用频率
	 * @param charList 待统计字集list（汉字以部件代码的array表示）
	 * @param useRateMap 使用频率Map(key为汉字String,value为使用频率百分比)
	 * @return BigDecimal 总使用率
	 */
	public static BigDecimal calTotalUseRate(List<String[]> charList,Map<String, BigDecimal> useRateMap) {
		BigDecimal totalUseRate = BigDecimal.ZERO;
		for (String[] singleChar : charList) {
			totalUseRate = totalUseRate.add(useRateMap.get(String.join(" ", singleChar)));
		}
		return totalUseRate;
	}
	
	/**
	 * 计算汉字singleChar在charArr的汉字范围内的构字频度
	 * @param singleChar 一个汉字（由该汉字的部件构成array）
	 * @param charArr 汉字集array(由汉字String构成的array,汉字内部的部件代码以空格分隔，汉字与汉字间以逗号分隔，如果有使用频率则再以#分隔)
	 * @return int 构字频度
	 */
	public static int calculateFreqOfChar(String[] singleChar, String[] charArr) {
		int count = 0;
		List<Integer> posList = new ArrayList<Integer>();
		for (String part : singleChar) {
			for (int i = 0; i < charArr.length; i++) {
				if (charArr[i].contains(part) && !posList.contains(i)) {
					count++;
					posList.add(i);
				}
			}
		}
		return count;
	}
	
	/**
	 * 计算字集origin中部件的覆盖次数
	 * @param origin 字集list（汉字以部件代码的array表示）
	 * @return Map<String, Integer> 部件覆盖次数Map(key为部件代码，value为部件被结果字集覆盖的次数)
	 */
	public static Map<String, Integer> countPartUsage(List<String[]> origin) {
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		for (String[] singleChar : origin) {
			for (String part : singleChar) {
				if (countMap.containsKey(part)) {
					countMap.replace(part, countMap.get(part)+1);
				} else {
					countMap.put(part, 1);
				}
			}
		}
		return countMap;
	}
}
