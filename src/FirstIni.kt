import java.io.File
import java.io.IOException


/**
 * ini 文件读写
 * @version 1.0
 * @author songdehuai
 */
class FirstIni {

    var file: File? = null
        set(value) {
            field = value
            readFile()
        }

    private var valueMaps = LinkedHashMap<String, LinkedHashMap<String, Any?>?>()

    constructor() {

    }

    constructor(file: File) {
        this.file = file
        readFile()
    }

    fun getAll(): LinkedHashMap<String, LinkedHashMap<String, Any?>?> {
        return valueMaps
    }

    fun getAllNode(): MutableSet<String> {
        return valueMaps.keys
    }

    fun getValue(nodeKey: String, key: String, defaultValue: String?): Any? {
        valueMaps[nodeKey]?.let {
            it[key]?.let { value ->
                return value
            }
        }
        return defaultValue
    }

    fun getValue(nodeKey: String, key: String): Any? {
        return getValue(nodeKey, key, null)
    }

    fun getNode(nodeKey: String): LinkedHashMap<String, Any?>? {
        return valueMaps[nodeKey]
    }

    fun addNode(nodeKey: String) {
        valueMaps[nodeKey] = LinkedHashMap<String, Any?>()
    }

    fun addNode(nodeKey: String, values: LinkedHashMap<String, Any?>) {
        valueMaps[nodeKey] = values
    }

    fun addValue(nodeKey: String, key: String, value: String) {
        valueMaps[nodeKey]?.put(key, value)
    }

    fun addValues(nodeKey: String, values: LinkedHashMap<String, Any?>) {
        valueMaps[nodeKey]?.putAll(values)
    }

    fun editValue(nodeKey: String, key: String, value: String) {
        valueMaps.get(nodeKey)?.put(key, value)
    }

    fun removeNode(nodeKey: String) {
        valueMaps.remove(nodeKey)
    }

    fun commit() {
        if (file == null) {
            throw IOException("File is null !!!")
        }
        writeTextToFile(file, "", true)
        valueMaps.forEach { maps ->
            writeTextToFile(file, createKey(maps.key))
            writeTextToFile(file, System.lineSeparator())
            maps.value?.forEach { valueMaps ->
                writeTextToFile(file, "${valueMaps.key} = ")
                writeTextToFile(file, "${valueMaps.value}")
                writeTextToFile(file, System.lineSeparator())
            }
            writeTextToFile(file, System.lineSeparator())
        }
    }

    private fun readFile() {
        var nodeTemp = ""
        file?.readLines()?.forEachIndexed { index, s ->
            if (s.isNotEmpty()) {
                if (isKey(s)) {
                    nodeTemp = replaceKey(s)
                    valueMaps[nodeTemp] = LinkedHashMap<String, Any?>()
                } else {
                    valueMaps[nodeTemp]?.put(getKey(s), getValue(s))
                }
            }
        }
    }

    private fun getValue(str: String): String {
        return str.split("=")[1].trim()
    }

    private fun getKey(str: String): String {
        return str.split("=")[0].trim()
    }

    private fun createKey(key: String): String {
        return "[${key}]"
    }

    private fun isKey(str: String): Boolean {
        return str.contains("[") && str.contains("]")
    }

    private fun replaceKey(str: String): String {
        return str.replace("[", "").replace("]", "").trim()
    }

    private fun writeTextToFile(file: File?, text: String, isNewFiles: Boolean = false) {
        if (isNewFiles) {
            file?.writeText(text)
        } else {
            file?.appendText(text)
        }
    }

}