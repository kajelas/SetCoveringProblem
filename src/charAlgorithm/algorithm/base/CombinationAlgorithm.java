package charAlgorithm.algorithm.base;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @filename   :CombinationAlgorithm.java
 *
 * 本类是组合函数。
 * 
 * @since    
 *
 * @see 
 *
 */
public class CombinationAlgorithm {
	
	/**
	 * 
	 * 组合函数递归算法
	 *
	 * @param totalNumber     待组合的字符串数组
	 * @param selectedNumber  选择的个数
	 * @return 
	 * @throws 
	 * @since
	 */
	public static List<String[]> recursion(String[] totalNumber,int selectedNumber) {
		List<String[]> returnList = new ArrayList<String[]>();
		int n = totalNumber.length;
		String[] init = totalNumber;
		String[] end = new String[selectedNumber];
		choose(0, 0, end, init, selectedNumber, n,returnList);
		return returnList;
	}

	/**
	 * 
	 * 递归算法
	 *
	 * @param k          递归开始下标
	 * @param l          递归开始下标
	 * @param end        临时存放数组
	 * @param init       待组合的字符串数组
	 * @param selectedNumber          选择的个数
	 * @param selectedNumber          总个数
	 * @param list       返回的List列表
	 * @param separator  分隔符
	 * @throws 
	 * @since
	 */
	private static void choose(int k, int l,String[] end,String[] init,int selectedNumber,int totalNumber,List<String[]> list) {
		if (l == selectedNumber) {
			String[] tempPartArr = new String[selectedNumber];
			for (int i = 0; i < selectedNumber; i++) {
				tempPartArr[i] = end[i];
			}
			list.add(tempPartArr);
		} else if (totalNumber - k == selectedNumber - l) {
			for (int i = 0; i < selectedNumber - l; i++) {
				end[i] = init[k + i];
			}
			String[] tempPartArr = new String[selectedNumber];
			for (int i = 0; i < selectedNumber; i++) {
				tempPartArr[i] = end[i];
			}
			list.add(tempPartArr);
		} else {
			end[l] = init[k];
			choose(k + 1, l + 1,end,init,selectedNumber,totalNumber,list);
			choose(k + 1, l,end,init,selectedNumber,totalNumber,list);
		}
	}

	
	/**
	 * 
	 * 组合函数求值(从denominator个数中生成numerator个数组合的总数)
	 * 
	 * @param denominator
	 *            分母
	 * @param numerator
	 *            分子
	 * 
	 * @return
	 * @throws
	 * @since
	 */
	public static int combination(int denominator, int numerator) {
		int result = 0;
		if (denominator < 0 || numerator < 0 || denominator < numerator) {
			return -1;
		}
		numerator = numerator < (denominator - numerator) ? numerator : denominator - numerator;
		if (numerator == 0) {
			return 1;
		}
		result = denominator;
		for (int i = 2, j = result - 1; i <= numerator; i++, j--) {
			result = result * j / i;
		}
		return result;
	}
	
//	public static void main(String[] args){
//		String[] list = {"0","1","2","3","4","5","6","7","8","9","10","11","12","13"};
//		List<String[]> test1 = recursion(list,5);
//		for (String[] con: test1) {
//			for (String part : con) {
//				System.out.print(part+",");
//			}
//			System.out.println();
//		}
//		System.out.println(test1.size());
//	}
}
