package charAlgorithm.model;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 最优字查找算法的结果对象
 * @author Kajelas
 *
 */
public class SolveSetCoveringProblemAlgorithmResult {
	//结果字集
	private CopyOnWriteArrayList<String[]> resultCharList;
	//未覆盖部件
	private String notCoveredParts;
	//已覆盖部件
	private String coveredParts;
	//部件覆盖次数Map
	private Map<String, Integer> partUsageCountMap;
	
	/**
	 * 获取结果字集（汉字以部件代码的array表示）
	 * @return CopyOnWriteArrayList<String[]>
	 */
	public CopyOnWriteArrayList<String[]> getResultCharList() {
		return resultCharList;
	}
	public void setResultCharList(CopyOnWriteArrayList<String[]> resultCharList) {
		this.resultCharList = resultCharList;
	}
	
	/**
	 * 获取未覆盖部件（以空格分隔）
	 * @return String
	 */
	public String getNotCoveredParts() {
		return notCoveredParts;
	}
	public void setNotCoveredParts(String notCoveredParts) {
		this.notCoveredParts = notCoveredParts;
	}
	
	/**
	 * 获取部件覆盖次数Map(key为部件代码，value为部件被结果字集覆盖的次数)
	 * @return Map<String, Integer>
	 */
	public Map<String, Integer> getPartUsageCountMap() {
		return partUsageCountMap;
	}
	public void setPartUsageCountMap(Map<String, Integer> partUsageCountMap) {
		this.partUsageCountMap = partUsageCountMap;
	}
	
	/**
	 * 获取已覆盖部件（以空格分隔）
	 * @return String
	 */
	public String getCoveredParts() {
		return coveredParts;
	}
	public void setCoveredParts(String coveredParts) {
		this.coveredParts = coveredParts;
	}
}
