package io.gitlab.arturbosch.detekt.sample.extensions.processors

import io.gitlab.arturbosch.detekt.api.Detektion
import io.gitlab.arturbosch.detekt.api.Finding
import io.gitlab.arturbosch.detekt.api.Notification
import io.gitlab.arturbosch.detekt.api.ProjectMetric
import io.gitlab.arturbosch.detekt.test.compileContentForTest
import org.assertj.core.api.Assertions
import org.jetbrains.kotlin.com.intellij.openapi.util.Key
import org.jetbrains.kotlin.com.intellij.util.keyFMap.KeyFMap
import org.junit.jupiter.api.Test

/**
 * @author Artur Bosch
 */
internal class QualifiedNameProcessorTest {

	@Test
	fun fqNamesOfTestFiles() {
		val ktFile = compileContentForTest(code)
		val processor = QualifiedNameProcessor()
		processor.onProcess(ktFile)
		processor.onFinish(listOf(ktFile), result)

		val data = result.getData(fqNamesKey)
		Assertions.assertThat(data).contains(
				"io.gitlab.arturbosch.detekt.sample.Foo",
				"io.gitlab.arturbosch.detekt.sample.Bar",
				"io.gitlab.arturbosch.detekt.sample.Bla")
	}
}

val result = object : Detektion {

	override val findings: Map<String, List<Finding>> = mapOf()
	override val notifications: Collection<Notification> = listOf()
	override val metrics: Collection<ProjectMetric> = listOf()

	private var userData = KeyFMap.EMPTY_MAP
	override fun <V> getData(key: Key<V>): V? = userData.get(key)

	override fun <V> addData(key: Key<V>, value: V) {
		userData = userData.plus(key, value)
	}

	override fun add(notification: Notification) {
		throw UnsupportedOperationException("not implemented")
	}

	override fun add(projectMetric: ProjectMetric) {
		throw UnsupportedOperationException("not implemented")
	}

}

val code = """
	package io.gitlab.arturbosch.detekt.sample

	class Foo {}
	object Bar {}
	interface Bla {}
"""