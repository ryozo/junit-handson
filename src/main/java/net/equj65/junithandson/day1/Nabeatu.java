package net.equj65.junithandson.day1;

/**
 * JUnit勉強会（ハンズオン）第一回で利用するサンプルコードです。
 * @author ryozo
 */
public class Nabeatu {
	// Handson4に伴う修正
	private int ahoCount;

	/**
	 * Handson4に伴う修正<br>
	 * AHOになるタイミングを指定してインスタンスを作成します。
	 * @param ahoCount
	 */
	public Nabeatu(int ahoCount) {
		this.ahoCount = ahoCount;
	}

	/**
	 * 引数が３の倍数、または３が含まれている場合"AHO"になります。（"AHO"を返します。）
	 * それ以外の場合は、引数を文字列表現で返します。
	 */
	public String aho(int message) {
		// Handson3に伴う修正
		if (message < 0) {
			throw new IllegalArgumentException("引数がマイナス値です");
		}

		// Handson4に伴う修正（ahoCountで割り切れたら"AHO")
		if (message % ahoCount == 0) {
			return "AHO";
		}
		String messageStr = String.valueOf(message);
		// Handson4に伴う修正（ahoCountを数値含んでいたら"AHO")
		if (messageStr.contains(String.valueOf(ahoCount))) {
			return "AHO";
		}
		return messageStr;
	}
}