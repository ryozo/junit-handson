package net.equj65.junithandson.day1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 * {@link Nabeatu}に対するハンズオン結果です。<br>
 * 
 * <pre>
 * 【テストクラスの解説】
 * 当テストクラスは構造化テストを利用し、以下の４クラスに分割しています。
 * 1."3"でAHOになるケース
 * 2."5"でAHOになるケース
 * 3.異常系（例外発生）のケース
 * 4.異常系(例外発生)の詳細を検証するケース
 * </pre>
 *
 * 4については当日解説しきれなかった箇所です。 発生した例外内のメッセージやCause検証を行いたい場合は参考としてください。<br>
 * また、テストコードが長いと思われる方もいるかと思いますが、 その場合は「Ctrl+o」でアウトラインを覗いてみてください。
 * 構造化テストによって分かりやすくテストケースが分類されていることが分かるかと思います。<br>
 *
 * 【構造化テストに関する補足】<br>
 * 構造化テストはネスト可能です。今回は２階層のテストとしていますが、3階層、4階層と更にネストすることもできます。
 * ただし、ネストしすぎると逆に読みにくくなる事がほとんどのため、最大でも3階層程度とした方が良いでしょう。
 * 
 * @author ryozo
 */
@RunWith(Enclosed.class)
public class NabeatuTest {

	/**
	 * 3でAHOになる場合の検証を行います。
	 * @author ryozo
	 */
	public static class AhoCountが3の場合 {
		/**
		 * テスト対象クラスです<br>
		 * このようにテスト対象クラスをテストクラスのフィールドで保持するのはOKです。
		 */
		Nabeatu sut;

		/**
		 * 全てのテストケース実施前に実行される初期化メソッドです。
		 * 当クラス内のテストケースは全てAhoCount=3が前提となっているため、このメソッドでまとめて初期化します。
		 */
		@Before
		public void ahoCountを3で初期化() {
			sut = new Nabeatu(3);
		}

		// 倍数の観点 -----------------------------------
		@Test
		public void ahoに3を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(3);

			// Verify
			assertThat(result, is("AHO"));
		}

		@Test
		public void ahoに12を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(12);

			// Verify
			assertThat(result, is("AHO"));
		}

		// 3の倍数ではないが3を含む数値の観点 ----------------------
		@Test
		public void ahoに13を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(13);

			// Verify
			assertThat(result, is("AHO"));
		}

		// 3の倍数でも3を含む数値でも無い数値の観点 ---------------
		@Test
		public void ahoに5を渡した場合は5が返る() {
			// Excersize
			String result = sut.aho(5);

			// Verify
			assertThat(result, is("5"));
		}
	}

	/**
	 * 5でAHOになる場合の検証を行います。
	 * @author ryozo
	 */
	public static class AhoCountが5の場合 {
		/** テスト対象クラスです */
		Nabeatu sut;

		@Before
		public void ahoCountを5で初期化() {
			sut = new Nabeatu(5);
		}

		// 倍数の観点 -----------------------------------
		@Test
		public void ahoに5を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(5);

			// Verify
			assertThat(result, is("AHO"));
		}

		@Test
		public void ahoに10を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(10);

			// Verify
			assertThat(result, is("AHO"));
		}

		// 5の倍数ではないが5を含む数値の観点 ----------------------
		@Test
		public void ahoに51を渡した場合はAHOが返る() {
			// Excersize
			String result = sut.aho(51);

			// Verify
			assertThat(result, is("AHO"));
		}

		// 5の倍数でも5を含む数値でも無い数値の観点 ---------------
		@Test
		public void ahoに5を渡した場合は5が返る() {
			// Excersize
			String result = sut.aho(6);

			// Verify
			assertThat(result, is("6"));
		}
	}

	/**
	 * 例外が発生するケースの検証を行います。
	 * @author ryozo
	 */
	public static class 例外発生ケースの検証 {
		Nabeatu sut;

		@Before
		public void before() {
			sut = new Nabeatu(3);
		}

		// 例外発生に関する検証
		// Testアノテーションのexpected属性を利用して指定した型の例外発生を検証する
		@Test(expected = IllegalArgumentException.class)
		public void ahoにマイナス値を渡した場合はExceptionが発生する() {
			sut.aho(-1);
		}

		@Test
		public void ahoに0を渡した場合はExceltionが発生せずAHOが返る() {
			// Excersize
			String result = sut.aho(0);

			// Verify
			assertThat(result, is("AHO"));
		}
	}

	/**
	 * このクラスは番外編です。 詳細な例外検証等を行う場合のサンプルです。
	 * @author ryozo
	 */
	public static class 詳細な例外検証 {
		/**
		 * 詳細なExceptionを検証するためのRule設定です。<br>
		 * {@link Test}アノテーションの{@code expected}引数は発生する例外のクラスは検証できますが、
		 * 例外に内包されるExceptionやMessageは検証できません。 これを検証する場合は
		 * {@link ExpectedException}を利用すると便利です。
		 *
		 * @see <a
		 *      href="http://d.hatena.ne.jp/hayassh/20110713/1310557092">参考サイト</a>
		 */
		@Rule
		public ExpectedException expectedException = ExpectedException.none();

		@Test
		public void ahoにマイナス値を渡した場合のExceptionメッセージ検証() {
			// Setup
			Nabeatu sut = new Nabeatu(3);

			// IllegsalArgumentExceptionが発生することを検証するよう予約
			expectedException.expect(IllegalArgumentException.class);
			// 所定のメッセージが設定されることを検証するよう予約
			// Causeを検証する場合は、expectCauseメソッドを利用すること
			expectedException.expectMessage(is("引数がマイナス値です"));

			// Excersize & Verify
			sut.aho(-1);
		}
	}
}