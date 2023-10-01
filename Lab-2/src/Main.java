import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Throwable {
        _FileReader FR = new _FileReader("./input.txt");
        String fstr = FR.ReadString();
        String sstr = FR.ReadString();
        StringTokenizer ST = new StringTokenizer(fstr);
        ArrayList<String> words = new ArrayList<String>();
        while (ST.hasMoreTokens())
        {
            words.add(ST.nextToken(sstr));
        }
        ArrayList<String> RussianWords = new ArrayList<String>();
        Pattern RussianWord = Pattern.compile("^[А-Яа-я]+$");
        ArrayList<String> Dates = new ArrayList<String>();
        Pattern DateWord = Pattern.compile("^(((0[1-9]|[1-2][0-9]|30)-(0[469]|(11)))|((0[1-9]|[1-2][0-9]|3[01])-(0[13578]|(1[02])))|((0[1-9]|[1-2][0-8])-(02)))-([1-9]\\d{0,2}|1\\d{3}|20[0-1]\\d|202[0-3])$");
        for(String item: words){
            Matcher matcher = RussianWord.matcher(item);
            if(matcher.matches())
            {
                RussianWords.add(item);
            }
        }
        for(String item: words){
            Matcher matcher = DateWord.matcher(item);
            if(matcher.matches())
            {
                Dates.add(item);
            }
        }
        _FileWriter FW = new _FileWriter("./output.txt");
        FW.WriteString("Words:");
        for(String item: words){
            FW.WriteString(item);
        }
        FW.WriteString("\nRussian words:");
        for(String item: RussianWords){
            FW.WriteString(item);
        }
        FW.WriteString("\nDates:");
        for(String item: Dates){
            FW.WriteString(item);
        }
        FW.WriteString("\nOld string:");
        FW.WriteString(fstr);
        String RussianWordInString = new String("[" + sstr + "]+[А-Яа-я]+[" + sstr + "]");
        Pattern FirstRussianWord = Pattern.compile(RussianWordInString);
        StringBuilder fstr_builder = new StringBuilder();
        fstr_builder.append(fstr);
        if (!RussianWords.isEmpty())
        {
            Matcher matcher = FirstRussianWord.matcher(fstr);
            boolean q = matcher.find();
            int i = matcher.end();
            fstr_builder.insert(matcher.end(), " " + Math.round(Math.random() * 1000) + " ");
        }
        else
        {
            fstr_builder.insert((fstr_builder.length() - 1) / 2, " " + Math.round(Math.random() * 1000) + " ");
        }
        fstr = fstr_builder.toString();
        int i = 0, size = 0, max_index = 0;
        for(String item: words){
            if(item.length() > size)
            {
                max_index = i;
                size = item.length();
            }
            ++i;
        }
        Pattern MaximumSizeWord = Pattern.compile(words.get(max_index));
        Matcher matcher = MaximumSizeWord.matcher(fstr);
        fstr = matcher.replaceAll("");
        FW.WriteString("\nNew string:");
        FW.WriteString(fstr);
        FR.CloseFile();
        FW.CloseFile();
    }
}