package com.ll.net.annotations

@Target(AnnotationTarget.TYPE,AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Pick(val value: String = "", val attr: String = "")