package com.ll.util

import com.ll.net.annotations.Pick
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.lang.Exception
import java.lang.reflect.Field

object JsoupUtil {
    fun <T : Any> convertHtml2Clazz(
            html: String,
            clazz: Class<T>
    ) {
        var t: T? = null
        var pickClazz: Pick
        try {
            val root: Document = Jsoup.parse(html)
            pickClazz = clazz.annotations.find { it is Pick } as Pick
            val classAttr = pickClazz.attr
            val classValue = pickClazz.value

            val rootNode: Element = getRootNode(root, classValue)
            var fields: Array<Field> = clazz.declaredFields
            t = clazz.newInstance()
            fields.forEach {

            }

        } catch (e: Exception) {
            println("convertHtml2Clazz Exception: ${e.message}")
        }
    }

    /**
     * 获取根节点,通常在类的注解上使用
     */
    private fun getRootNode(rootDocument: Document, value: String): Element = rootDocument.selectFirst(value)
}