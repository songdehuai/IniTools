import java.io.File
import java.lang.reflect.Method
import java.lang.reflect.Parameter
import kotlin.test.currentStackTrace

object IniMainTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val ini = FirstIni(File("./test.ini"))
        val node = "node1"
        println(ini.getNode(node)?.get("testKey"))
        println(ini.getNode(node))
    }

}


