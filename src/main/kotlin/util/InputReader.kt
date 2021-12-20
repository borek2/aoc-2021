package util

import java.io.FileInputStream

class InputReader {

    inline fun <T> read(
        assetName: String,
        convertLine: (String) -> T
    ): List<T> {
        return try {
            javaClass.getResource("/$assetName")?.file?.let { file ->
                FileInputStream(file).bufferedReader().use { reader ->
                    reader.lines().toList().map(convertLine)
                }
            } ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}