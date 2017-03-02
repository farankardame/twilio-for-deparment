package com.twilio.phonetree.servlet.ivr;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.Gather;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

public class WelcomeServlet extends HttpServlet {
	public static String SYSTEM_NAME = "JSA"; 
    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
            throws IOException {
    	
        VoiceResponse response = new VoiceResponse.Builder()
        		.say(new Say.Builder("Welcome to the Ministry of Magic. Before we can continue we need to know your National Insurance number, as such  please can you key in the numbers from your national insurance number, for example if your national insurance number is AB123456C please key in 123456.")
        				.voice(Say.Voice.ALICE)
                        .language(Say.Language.EN_GB)
        				.build())                
        		.gather(new Gather.Builder()
        				.action("/commuter/nino")
                        .numDigits(6)
                        .build()).build();

        servletResponse.setContentType("text/xml");
        try {
            servletResponse.getWriter().write(response.toXml());
        } catch (TwiMLException e) {
            throw new RuntimeException(e);
        }
    }
    
}

