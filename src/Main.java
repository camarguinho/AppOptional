import java.util.Optional;

public class Main {

    public static void main(String[] args){
        /*Optional<String> name = Optional.ofNullable("Camargo");
        System.out.println(name.orElse("N/A"));*/

        /*Optional<String> _nome = Optional.of("");
        System.out.println(_nome.get());*/

        /*Optional<Integer> length = name.map(String::length);
        System.out.println(length.orElse(0));*/

        //transformValueMap();
        transformValueFlatMap();
    }

    /**
     * Transforma o valor se existir.
     * map(Function<T, R>)
     *
     * Caso de uso:
     * -Normalização
     * -Conversão de tipos
     * -Extração de campos
     *
     */
    private static void transformValueMap(){
        Optional<User> user = findUser();
        Optional<Integer> length = user.map(user1 -> user1.name().length());
        System.out.println(length.get());
    }

    /**
     * Usado quando a função já retorna um Optional.
     * flatMap(Function<T, Optional<R>>)
     *
     * Caso de uso:
     * -Encadeamento de Optionals
     * -APIs que retornam Optional
     * -Evitar Optional<Optional<T>>
     */
    private static void transformValueFlatMap(){
        Optional<User> user = findUser();
        Optional<String> email = user.flatMap(User::email);
        System.out.println(email.get());
    }

    private static Optional<User> findUser(){
       return Optional.of(new User("Camargo", Optional.of("linkcamargo@gmail.com")));
    }
}
