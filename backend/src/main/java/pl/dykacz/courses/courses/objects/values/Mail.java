package pl.dykacz.courses.courses.objects.values;

import org.springframework.lang.NonNull;

public class Mail {
    private static final String MAIL_CHAR = "@";

    public static class Builder{
        private final Name mail;

        public Builder(@NonNull String mail){
            this(new Name(mail));
        }
        public Builder(@NonNull Name mail){
            this.mail = mail;
        }

        public Mail build(){
            final String[] parts = this.mail.toString().split(MAIL_CHAR);
            if(parts.length != 2) throw  new IllegalArgumentException("Invalid mail");

            return new Mail(new Name(parts[0]), new Name(parts[1]));
        }
    }

    private final Name firstPart;
    private final Name secondPart;

    private Mail(@NonNull Name firstPart,@NonNull Name secondPart) {
        if(firstPart.toString().contains(MAIL_CHAR)) throw new IllegalArgumentException("firstPart must not contain "+MAIL_CHAR);
        if(secondPart.toString().contains(MAIL_CHAR)) throw new IllegalArgumentException("secondPart must not contain "+MAIL_CHAR);

        this.firstPart = firstPart;
        this.secondPart = secondPart;
    }

    @Override
    public String toString() {
        return this.firstPart+MAIL_CHAR+this.secondPart;
    }
}
