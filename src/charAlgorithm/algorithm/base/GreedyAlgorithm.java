package charAlgorithm.algorithm.base;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import charAlgorithm.model.SolveSetCoveringProblemAlgorithmResult;
import charAlgorithm.util.CalculateUtil;

/**
 * 针对查找最优汉字集的贪婪算法
 * @author Kajelas
 *
 */
public class GreedyAlgorithm {
	
	/**
	 * 运用贪婪算法在给定charList中找出最能覆盖notCoveredParts的最优汉字集（基本思想是循环找出符合条件的最优汉字）
	 * @param charList 汉字List（汉字以部件代码的array表示）
	 * @param notCoveredParts 未被覆盖的部件
	 * @param freqMap 构字频率Map
	 * @param useRateMap 使用频率Map
	 * @param maxCharNum 最优汉字集的最大字数
	 * @return SolveSetCoveringProblemAlgorithmResult 算法结果
	 * @see SolveSetCoveringProblemAlgorithmResult
	 */
	public SolveSetCoveringProblemAlgorithmResult findCoveringSetUsingGreedy(CopyOnWriteArrayList<String[]> charList, String notCoveredParts, Map<String[], Integer> freqMap, Map<String[], BigDecimal> useRateMap,Integer maxCharNum) {
		int charNum = charList.size();
		int singlePartLength = charList.get(0)[0].length();
		CopyOnWriteArrayList<String[]> newCharList = new CopyOnWriteArrayList<String[]>();
		
		//每次循环找出一个当前条件下的最优汉字，最优的标准请见findCurrentMaxCover()方法中的排序顺序
		while (notCoveredParts.length() >= singlePartLength && newCharList.size() < charNum && (maxCharNum == null || newCharList.size() < maxCharNum)) {
			int position = findCurrentMaxCover(charList, notCoveredParts,freqMap,useRateMap);
			
			if (position < 0) {
				break;
			}
			
			//将已找到的汉字放到一个新的汉字list中，然后从未覆盖部件中移除这个字覆盖的部件，并且从初始汉字list中移除这个字
			newCharList.add(charList.get(position));
			notCoveredParts = removeCoveredParts(charList.get(position), notCoveredParts);			
			charList.remove(position);
		}
		
		//组装算法结果	
		SolveSetCoveringProblemAlgorithmResult result = new SolveSetCoveringProblemAlgorithmResult();
		result.setNotCoveredParts(notCoveredParts);
		result.setResultCharList(newCharList);
		result.setPartUsageCountMap(CalculateUtil.countPartUsage(newCharList));
		
		//对于每次找到的结果，去除冗余覆盖字
		RemoveRedundantAlgorithm removeRedundantAlgorithmForChar = new RemoveRedundantAlgorithm();
		removeRedundantAlgorithmForChar.removeRedundantParts(charList, result);
		
		return result;
	}
	
	//寻找当前最优汉字的方法
	private Integer findCurrentMaxCover(CopyOnWriteArrayList<String[]> charList, String notCoveredParts, Map<String[], Integer> freqMap, Map<String[], BigDecimal> useRateMap) {
		//int currentPartsNum = 0;
		int maxCoveringNum = 0;
		int maxCoverPosition = -1;
		int maxCoveringFreq = 0;
		BigDecimal maxCoveringUseRate = BigDecimal.ZERO;
		Map<Integer, Integer> coveringNumMap = new HashMap<Integer, Integer>();
		
		for (int i = 0;i < charList.size(); i++) {
			int currentCoveringNum = getCurrentCharCover(charList.get(i), notCoveredParts);
			Integer currentFreq = freqMap.get(charList.get(i));
			BigDecimal currentUseRate = useRateMap.get(charList.get(i));
			coveringNumMap.put(i, currentCoveringNum);
			//currentPartsNum = charList.get(i).length;
			
			//比较顺序:覆盖部件数>所含部件可组字数>使用频率
			if (currentCoveringNum > maxCoveringNum
					|| (currentCoveringNum != 0 && currentCoveringNum == maxCoveringNum && (currentFreq > maxCoveringFreq
							|| (currentFreq != 0 && currentFreq == maxCoveringFreq && currentUseRate != null && maxCoveringUseRate != null && currentUseRate.compareTo(maxCoveringUseRate) > 0)))) {

			//比较顺序:覆盖部件数>使用频率>所含部件可组字数
		    //if (currentCoveringNum > maxCoveringNum
		    //	|| (currentCoveringNum != 0 && currentCoveringNum == maxCoveringNum && (currentUseRate != null && maxCoveringUseRate != null && currentUseRate.compareTo(maxCoveringUseRate) > 0)
		    //		|| (currentUseRate != null && maxCoveringUseRate != null && currentUseRate.compareTo(BigDecimal.ZERO) != 0 && currentUseRate.compareTo(maxCoveringUseRate) == 0 && currentFreq > maxCoveringFreq))) {
				maxCoveringNum = currentCoveringNum;
				maxCoverPosition = i;
				maxCoveringFreq = currentFreq;
				maxCoveringUseRate = currentUseRate;
			}
			
			//比较的终止条件，用于提高比较效率，但是当排序标准不再是简单的部件数时，就不可用了
			//if (currentPartsNum == currentCoveringNum)
			//	break;
			//else if (maxCoveringNum == currentPartsNum)
			//	break;
		}
		
		return maxCoverPosition;	
	}
	
	//计算某个汉字能覆盖多少部件的方法
	private int getCurrentCharCover(String[] singleChar, String notCoveredParts) {
		int count = 0;
		for (int i = 0; i < singleChar.length; i++) {
			if (notCoveredParts.contains(singleChar[i]))
				count++;
		}
		return count;
	}
	
	//从未覆盖部件集中去除已覆盖部件的方法
	private String removeCoveredParts(String[] singleChar, String notCoveredParts) {
		for (int i = 0; i < singleChar.length; i++) {
			notCoveredParts = notCoveredParts.replace(singleChar[i]+" ", "").replace(singleChar[i], "");
		}
		return notCoveredParts;
	}
}
