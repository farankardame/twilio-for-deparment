package com.twilio.phonetree.servlet.commuter;

import com.twilio.phonetree.servlet.common.Redirect;
import com.twilio.twiml.Dial;
import com.twilio.twiml.Gather;
import com.twilio.twiml.Number;
import com.twilio.twiml.Play;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NinoServlet extends HttpServlet {
	public static String NINO = "";
	
   	@Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IOException {

        String selectedOption = servletRequest.getParameter("Digits");

        
        NINO = "AB"+selectedOption+"B";
       /* RestTemplate restTemplate = new RestTemplate(); 
        String apiResponse = restTemplate.getForObject(url, String.class);*/
        
        VoiceResponse response = new VoiceResponse.Builder()
                .say(new Say.Builder(
                		"Thanks, please can you now key in your data of birth, for example if your birthday is 15th October 1987 please key in 11101987")
                        .voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
                        .build())
                .gather(new Gather.Builder()
                        .action("/commuter/dob")
                        .numDigits(8)
                        .build()).build();
        servletResponse.setContentType("text/xml");
        try {
            servletResponse.getWriter().write(response.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }
}

