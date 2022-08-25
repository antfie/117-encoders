package com.veracode.afielding.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ExampleController
{
	Logger logger = LoggerFactory.getLogger(ExampleController.class);

	@GetMapping(value = "/")
	public String index(Model model, String untrustedInput) throws JsonProcessingException {
		String payload = "test\n\rV\talue here is a space.!<@ 'string' \"string2\" :$# end with a space> ";
		logger.info("Payload: >>>{}<<<", payload);

		TestModel testJsonModel = new TestModel(untrustedInput, payload);
		String json = new ObjectMapper().writeValueAsString(testJsonModel);
		logger.info("JSON: >>>{}<<<", json);

		logger.info("org.owasp.encoder.Encode.forJava: >>>{}<<<", org.owasp.encoder.Encode.forJava(json));
		logger.info("org.owasp.esapi.StringUtilities.replaceLinearWhiteSpace: >>>{}<<<", org.owasp.esapi.StringUtilities.replaceLinearWhiteSpace(json));
		logger.info("org.springframework.util.StringUtils.trimAllWhitespace: >>>{}<<<", org.springframework.util.StringUtils.trimAllWhitespace(json));
		logger.info("org.apache.commons.lang3.StringUtils.deleteWhitespace: >>>{}<<<", org.apache.commons.lang3.StringUtils.deleteWhitespace(json));
		logger.info("org.apache.commons.lang3.StringUtils.normalizeSpace: >>>{}<<<", org.apache.commons.lang3.StringUtils.normalizeSpace(json));
		logger.info("org.apache.commons.lang.StringUtils.deleteWhitespace: >>>{}<<<", org.apache.commons.lang.StringUtils.deleteWhitespace(json));
		logger.info("org.apache.commons.lang.StringUtils.normalizeSpace: >>>{}<<<", org.apache.commons.lang.StringUtils.normalizeSpace(json));
		logger.info("org.apache.commons.text.StringEscapeUtils.escapeJson: >>>{}<<<", org.apache.commons.text.StringEscapeUtils.escapeJson(json));

		model.addAttribute("data", json);
		return "index";
	}
}