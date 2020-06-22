package restparents


import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.Assert
import org.springframework.web.context.WebApplicationContext
import java.io.IOException
import javax.servlet.Filter


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
abstract class ControllerParent {
    @get:Rule var restDocumentation = JUnitRestDocumentation()
    @get:Rule var name = TestName()
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var filters: List<Filter>
    @Autowired
    protected lateinit var webApplicationContext: WebApplicationContext

    @Before
    @Throws(IOException::class)
    fun setUp() {
        val builder = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply<DefaultMockMvcBuilder>(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint())
                ).addFilters<DefaultMockMvcBuilder>(*filters.toTypedArray())
        mockMvc = builder.build()

    }
}

