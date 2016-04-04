package charAlgorithm.algorithm.base;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import charAlgorithm.model.SolveSetCoveringProblemAlgorithmResult;
import charAlgorithm.util.CalculateUtil;

/**
 * 随机跳坑算法
 * @author Kajelas
 *
 */
public class RandomOffTrapAlgorithm {
	
	/**
	 * 随机跳坑算法（基本思想是移除随机一部分汉字，对于这时产生的未覆盖部件，运用贪婪算法找到覆盖这些部件的最优汉字，如果新的最优汉字数比原先的最优汉字数少，就记下新的最优汉字）。该算法需要循环调用多次以取得更优结果。
	 * @param origin 最初的汉字集（汉字以部件代码的array表示）
	 * @param result 经过贪婪算法的最优汉字集计算的结果对象
	 * @param removePercent 跳坑中去除汉字的比例
	 * @param freqMap 构字频率Map
	 * @param useRateMap 使用频率Map
	 * @param random 随机数种子
	 * @param maxCharNum 最优汉字集的最大汉字数
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void randomOffTrap(CopyOnWriteArrayList<String[]> origin, SolveSetCoveringProblemAlgorithmResult result, double removePercent,
			Map<String[], Integer> freqMap, Map<String[], BigDecimal> useRateMap,Random random,Integer maxCharNum) {
		GreedyAlgorithm greedyAlgorithmForChar = new GreedyAlgorithm();
		CopyOnWriteArrayList<String[]> originCopy = (CopyOnWriteArrayList<String[]>) origin.clone();
		CopyOnWriteArrayList<String[]> listAfterGreedyCopy = (CopyOnWriteArrayList<String[]>) result.getResultCharList().clone();
		
		//随机移除removePercent比例的汉字
		String notCoveredParts = removeRandomItems(originCopy, listAfterGreedyCopy, result.getPartUsageCountMap(), removePercent,random);
		
		//对新的未覆盖部件集进行贪婪算法求解
		SolveSetCoveringProblemAlgorithmResult newResult = greedyAlgorithmForChar.findCoveringSetUsingGreedy(originCopy, notCoveredParts, freqMap, useRateMap,maxCharNum);
		
		//根据求解结果的集合大小确定是否保留新的汉字集
		if (newResult.getResultCharList().size()+listAfterGreedyCopy.size() < result.getResultCharList().size()) {
			listAfterGreedyCopy.addAll(newResult.getResultCharList());
			origin.clear();
			result.getResultCharList().clear();
			result.getPartUsageCountMap().clear();
			
			origin.addAll(originCopy);
			result.getResultCharList().addAll(listAfterGreedyCopy);
			result.getPartUsageCountMap().putAll(CalculateUtil.countPartUsage(listAfterGreedyCopy));
		}
	}
	
	
	//随机移除一部分汉字
	private String removeRandomItems(List<String[]> origin, List<String[]> listAfterGreedy, Map<String, Integer> countMap, double removePercent,Random random) {
		int number = (int)(listAfterGreedy.size() * removePercent);
		int index;
		String notCoveredPart = "";
		for (int i = 0; i < number; i++) {
			index = random.nextInt(listAfterGreedy.size());
			String[] singleChar = listAfterGreedy.get(index);
			for (String part : singleChar) {
				countMap.replace(part, countMap.get(part)-1);
				if (countMap.get(part) < 1) {
					notCoveredPart += part + " ";
				}
			}
			origin.add(singleChar);
			listAfterGreedy.remove(singleChar);
		}
		
		return notCoveredPart.substring(0,notCoveredPart.length()-1);
	}
}
