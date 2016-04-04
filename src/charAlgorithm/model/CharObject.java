package charAlgorithm.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import charAlgorithm.util.CalculateUtil;


/**
 * 用以表示子集属性的对象
 * @author Kajelas
 *
 */
public class CharObject {
	//构字频度Map
	private Map<String, Integer> freqMap = new HashMap<String, Integer>();
	//使用频率Map
	private Map<String, BigDecimal> useRateMap = new HashMap<String, BigDecimal>();
	//汉字List
	private List<String[]> charList = new CopyOnWriteArrayList<String[]>();
	//汉字Array
	private String[] charArr = {};
	
	/**
	 * 初始化对象
	 * @param chars 汉字String(汉字内部的部件代码以空格分隔，汉字与汉字间以逗号分隔，如果有使用频率则再以#分隔)
	 */
	public CharObject(String chars) {
		initialize(chars);
	}
	
	private void initialize(String chars) {
		charArr = chars.split("\\,");
		
		for (String singleChar : charArr) {
			String[] tempArr = singleChar.split("\\#");
			String[] singleParts = tempArr[0].split(" ");
			charList.add(singleParts);
		}
	}
	
	/**
	 * 构造使用频率Map
	 * @return CharObject
	 */
	public CharObject countUseRate() {
		for (String singleChar : charArr) {
			String[] tempArr = singleChar.split("\\#");
			
			if (tempArr.length > 1) {
				useRateMap.put(tempArr[0], new BigDecimal(tempArr[1].replace("%", "")));
			}
		}
		return this;
	}
	
	/**
	 * 构造构字频度Map
	 * @return CharObject
	 */
	public CharObject countFreq() {
		for (String singleChar : charArr) {
			String[] tempArr = singleChar.split("\\#");
			String[] singleParts = tempArr[0].split(" ");
			
			freqMap.put(tempArr[0],CalculateUtil.calculateFreqOfChar(singleParts, charArr));
		}
		return this;
	}


	/**
	 * 获取字集（汉字以部件代码的array表示）
	 * @return List<String[]>
	 */
	public List<String[]> getCharList() {
		return charList;
	}

	public void setCharList(List<String[]> charList) {
		this.charList = charList;
	}

	/**
	 * 获取构字频度Map(key为汉字，value为频度)
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getFreqMap() {
		return freqMap;
	}

	public void setFreqMap(Map<String, Integer> freqMap) {
		this.freqMap = freqMap;
	}

	/**
	 * 获取使用频率Map(key为汉字，value为百分比)
	 * @return Map<String, BigDecimal>
	 */
	public Map<String, BigDecimal> getUseRateMap() {
		return useRateMap;
	}

	public void setUseRateMap(Map<String, BigDecimal> useRateMap) {
		this.useRateMap = useRateMap;
	}
}
