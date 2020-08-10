## 基于kotlin实现的简单 `ini` 文件读写,仅有一个类


#### 1,写入

```kotlin
val ini = FirstIni(File("./test.ini"))
val node = "node1"
ini.addNode(node)
ini.addValue(node,"testKey","哈哈哈")
ini.commit()
```

```
[node1]
testKey = 哈哈哈
```
`commit`方法会自己创建出来file


#### 2,读取

文件内容
```
[node1]
testKey = 哈哈哈
```

```kotlin
 val ini = FirstIni(File("./test.ini"))
 val node = "node1"
 println(ini.getNode(node)?.get("testKey"))
 println(ini.getNode(node))
```
输出
```
哈哈哈
{testKey=哈哈哈}
```

####使用方式

    推荐直接导入 FirstIni.kt 使用,如果你在使用Java,拥抱kotlin吧！或者你也可以自己实现一个Java版本.
    只需要实现 代码中kotlin特有的几个方法,即 `writeTextToFile`和`readFile`