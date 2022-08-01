import com.vdurmont.emoji.EmojiParser;

public enum Emoji {
    MINSK (":crown:"),
    VITEBSK (":new_moon_with_face:"),
    GRODNO (":blossom:"),
    MOGILEV (":rainbow:"),
    GOMEL (":sunny:"),
    BREST (":dizzy:");
    private String value;

    public String get(){
        return EmojiParser.parseToUnicode(value);
    }

    Emoji(String value) {
        this.value = value;
    }
}
