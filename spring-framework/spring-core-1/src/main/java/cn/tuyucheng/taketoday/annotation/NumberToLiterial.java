package cn.tuyucheng.taketoday.annotation;

public class NumberToLiterial {

	public static void main(String[] args) {
		System.out.println(secToString(95231));
	}

	public static String secToString(long sec) {
		if (sec < 60)
			return sec + "秒";
		if (sec < 3600)
			return (sec / 60) + "分" + secToString(sec % 60);
		else if (sec < 3600 * 24)
			return (sec / 60 / 60) + "时" + secToString(sec % (60 * 60));
		else
			return (sec / 60 / 60 / 24) + "天" + secToString(sec % (60 * 60 * 24));
	}
}