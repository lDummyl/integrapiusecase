package restparents


import org.apache.commons.lang.StringUtils
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultHandler

class CustomResultHandler private constructor(private val name: String,
                                              private val mvcResultFunction: (MvcResult) -> String,
                                              private val nameHandlerFunction: (String, Array<out Snippet>) -> ResultHandler) : ResultHandler {

    @Throws(Exception::class)
    override fun handle(mvcResult: MvcResult) {
        nameHandlerFunction.invoke(mvcResultFunction.invoke(mvcResult) + "/" + name, arrayOf()).handle(mvcResult)
    }

    companion object {
        private val DEFAULT_MVC_RESULT_FUNCTION = { mvcResult: MvcResult ->
            StringUtils.replaceChars(
                    StringUtils.substring(
                            mvcResult.request.pathInfo.toString(),
                            1),
                    "{}",
                    "()"
            )
        }

        fun handleResult(name: String,
                         mvcResultFunction: (MvcResult) -> String,
                         nameHandlerFunction: (String, Array<out Snippet>) -> ResultHandler): CustomResultHandler {
            return CustomResultHandler(name, mvcResultFunction, nameHandlerFunction)
        }

        fun handleResult(name: String,
                         nameHandlerFunction: (String, Array<out Snippet>) -> ResultHandler): CustomResultHandler {
            return CustomResultHandler(name, DEFAULT_MVC_RESULT_FUNCTION, nameHandlerFunction)
        }
    }
}
