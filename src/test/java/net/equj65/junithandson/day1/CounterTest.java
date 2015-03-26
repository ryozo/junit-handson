package net.equj65.junithandson.day1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link Counter}のハンズオン結果です。<br>
 * 当クラスは書籍「JUnit実践入門」を出典としています。
 * 
 * @author ryozo
 */
@RunWith(Enclosed.class)
public class CounterTest {

	public static class 初期状態の場合 {
		Counter sut;

		@Before
		public void setUp() {
			sut = new Counter();
		}

		@Test
		public void incrementは1を返す() {
			assertThat(sut.increment(), is(1));
		}
	}

	public static class incrementが1回実行された場合 {
		Counter sut;

		@Before
		public void setUp() {
			sut = new Counter();
			sut.increment();
		}

		@Test
		public void incrementは2を返す() {
			assertThat(sut.increment(), is(2));
		}
	}

	public static class incrementが50回実行された場合 {
		Counter sut;

		@Before
		public void setUp() {
			sut = new Counter();
			// java8ではないのでfor文・・
			for (int i = 0; i < 50; i++)
				sut.increment();
		}

		@Test
		public void incrementで51が取得できる() {
			assertThat(sut.increment(), is(51));
		}
	}

}