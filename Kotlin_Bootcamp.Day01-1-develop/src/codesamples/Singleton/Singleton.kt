object Singleton {
    val string: String by lazy { "Lazy singleton" } // "by" is one of standart Kotlin delegates you can find in official documentation. You'll work with delegates further.
}