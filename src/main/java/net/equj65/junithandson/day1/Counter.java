package net.equj65.junithandson.day1;

/**
 * ハンズオン5-1の実装クラスです。
 * 当クラスは状態を保持し、メソッドの実行に副作用(countのインクリメント）を伴います。
 * @author ryozo
 */

public class Counter {
	int count = 0;
	public int increment() {
		return ++count;
	}
}