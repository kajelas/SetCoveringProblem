package charAlgorithm.algorithm.base;

import java.util.List;
import charAlgorithm.model.SolveSetCoveringProblemAlgorithmResult;

/**
 * 移除汉字集中冗余覆盖的算法
 * @author Kajelas
 *
 */
public class RemoveRedundantAlgorithm {
	
	/**
	 * 移除移除汉字集listAfterGreedy中的冗余覆盖字
	 * @param origin 原汉字集（汉字以部件代码的array表示）
	 * @param result 经过贪婪算法的最优汉字集计算的结果对象
	 * @return void
	 */
	public void removeRedundantParts(List<String[]> origin, SolveSetCoveringProblemAlgorithmResult result) {		
		//倒序去除，因为汉字集中排序越靠前的字越优质，也就越应该保留
		//去除逻辑是如果去除了这个字，没有任何一个部件的覆盖次数减到0，那么就是冗余的。这时要更新countMap，再进行下一个字的判断
		for (int i = result.getResultCharList().size()-1; i >= 0; i--) {
			String[] singleChar = result.getResultCharList().get(i);
			int redundantCount = 0;
			for (String part : singleChar) {
				if (result.getPartUsageCountMap().get(part) > 1) {
					redundantCount++;
				}
			}
			if (redundantCount == singleChar.length) {
				for (String part : singleChar) {
					result.getPartUsageCountMap().replace(part, result.getPartUsageCountMap().get(part)-1);
				}
				result.getResultCharList().remove(singleChar);
				origin.add(singleChar);
			}
		}
	}
}
