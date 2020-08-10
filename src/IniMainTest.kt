import java.lang.reflect.Method
import java.lang.reflect.Parameter
import kotlin.test.currentStackTrace

object IniMainTest {
    @JvmStatic
    fun main(args: Array<String>) {
        test("test111", "哈哈哈")
    }

    fun test1(@Param("testssss") name: String) {

    }

    fun test(@Param("name") name: String, @Param("account") account: String) {
        getMethodsName(currentStackTrace().first())
    }


    private fun getMethodsName(stackTraceElement: StackTraceElement) {
        val methods: Array<Method> = javaClass.declaredMethods
        methods.forEach { methods ->
            if (methods.name == stackTraceElement.methodName) {
                val parameters: Array<Parameter> = methods.parameters
                parameters.forEach { parameter ->
                    println(parameter.name)
                    val annotations: Array<Annotation> = parameter.declaredAnnotations
                    annotations.forEach {
                        if (it is Param) {
                            println(it.name)
                        }
                    }
                }
            }
        }
    }
}


