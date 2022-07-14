package com.vttp2022.ssfworkshop3.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.vttp2022.ssfworkshop3.model.Contact;

@Component("contacts")
public class Contacts {
    private static final Logger logger = LoggerFactory.getLogger(Contacts.class);

    public void saveContact(Contact ctc, Model model, ApplicationArguments appArgs) {
        String dataFileName = ctc.getId();
        Set<String> opNames = appArgs.getOptionNames();
        String[] opNamesArr = opNames.toArray(new String[opNames.size()]);
        List<String> opValues = appArgs.getOptionValues(opNamesArr[0]);
        String[] opValuesArr = opValues.toArray(new String[opValues.size()]);

        PrintWriter printwriter = null;
        try {
            FileWriter filewriter = new FileWriter(opValuesArr[0] + "/" + dataFileName);
            printwriter = new PrintWriter(filewriter);
            printwriter.println(ctc.getName());
            printwriter.println(ctc.getEmail());
            printwriter.println(ctc.getPhoneNumber());
            printwriter.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            printwriter.close();
        }
        model.addAttribute("contact", new Contact(ctc.getId(), ctc.getName(), ctc.getEmail(), ctc.getPhoneNumber()));
    }
}
