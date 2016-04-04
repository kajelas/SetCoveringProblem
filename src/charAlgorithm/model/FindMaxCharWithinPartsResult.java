package charAlgorithm.model;
import java.util.List;

/**
 * 在指定范围内找出最大构字部件的算法结果对象
 * @author Kajelas
 *
 */
public class FindMaxCharWithinPartsResult {
	
	//找出的部件
	private String[] parts;
	//由找出的部件构成的字集
	private List<String[]> constructedCharList;

	/**
	 * 获取字集（汉字以部件代码的array表示）
	 * @return List<String[]>
	 */
	public List<String[]> getConstructedCharList() {
		return constructedCharList;
	}
	public void setConstructedCharList(List<String[]> constructedCharList) {
		this.constructedCharList = constructedCharList;
	}
	
	/**
	 * 获取找出的部件
	 * @return String[]
	 */
	public String[] getParts() {
		return parts;
	}
	public void setParts(String[] parts) {
		this.parts = parts;
	}
}
