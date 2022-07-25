package com.vttp2022.ssfworkshop3.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

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

    public void getContactById(Model model, String contactId, ApplicationArguments appArgs) {
        Contact ctc = new Contact();
        try {
            Path filePath = new File(getDataDir(appArgs) + "/" + contactId).toPath();
            Charset charset = Charset.forName("UTF-8");
            List<String> stringList = Files.readAllLines(filePath, charset);
            ctc.setId(contactId);
            ctc.setName(stringList.get(0));
            ctc.setEmail(stringList.get(1));
            ctc.setPhoneNumber(Integer.parseInt(stringList.get(2)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact info not found.");
        }

        model.addAttribute("contact", ctc);
    }

    private String getDataDir(ApplicationArguments appArgs) {
        Set<String> opsNames = appArgs.getOptionNames();
        String[] optNamesArr = opsNames.toArray(new String[opsNames.size()]);
        List<String> optValues = appArgs.getOptionValues(optNamesArr[0]);
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        return optValuesArr[0];
    }
}
