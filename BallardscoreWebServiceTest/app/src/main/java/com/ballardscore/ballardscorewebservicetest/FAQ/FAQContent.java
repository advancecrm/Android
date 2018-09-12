package com.ballardscore.ballardscorewebservicetest.FAQ;

import com.ballardscore.ballardscorewebservicetest.FAQListActivity;
import com.ballardscore.ballardscorewebservicetest.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Copied from DummyContent
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 */

public class FAQContent {
    public static String success="";    /** web service result */
    /**
     * An array of FAQ items.
     */
    public static final List<FAQContent.FAQItem> ITEMS = new ArrayList<FAQContent.FAQItem>();

    /**
     * A map of FAQ items, by ID.
     */
    public static final Map<String, FAQContent.FAQItem> ITEM_MAP = new HashMap<String, FAQContent.FAQItem>();

    static {
        // Parse web service response into FAQ Items
        success=parseJson(FAQListActivity.rslt);
    }


    private static String parseJson(String result) {
        String success = "error";
        try {

            // Remove HTML tags from the response string
            String json = result.replaceAll("\\<.*?>", "");

            // Format questions and answers properly
            json = json.replace("&nbsp;", "");
            json = json.replaceAll("\\.", ". ").replaceAll("\\.  ", "\\. ");
            json = json.replaceAll("  ", " ");
            json = json.replaceAll(" :", ":");
            json = json.replace("Dr. BallardReferences", "References");

            JSONArray jaray = new JSONArray(json);
            //queAnsList = new ArrayList<>();
            if (jaray.length() > 0) {
                for (int i = 0; i < jaray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject job = jaray.getJSONObject(i);
                    String question = job.getString(Constants.QUESTION);
                    String answer = job.getString(Constants.ANSWER);

                    map.put(Constants.QUESTION, question);
                    map.put(Constants.ANSWER, answer);

                    //queAnsList.add(map);
                    addItem(createFAQItem(i+1, question, answer));
                }
                //global.setQueAnsList(queAnsList);
            }
            success = "true";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return success;
    }

    private static void addItem(FAQContent.FAQItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static FAQContent.FAQItem createFAQItem(int position, String q, String a) {
        return new FAQContent.FAQItem(String.valueOf(position), q, a);
    }


    /**
     * A FAQ item representing a piece of content.
     */
    public static class FAQItem {
        public final String id;
        public final String content;
        public final String details;

        public FAQItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }


}
