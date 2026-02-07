import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Regras de ouro antes de começar
 * Optional é para retornos de métodos.
 * Não use em atributos, parâmetros ou coleções.
 *
 * Optional não é para tudo.
 * É para quando a ausência de valor faz parte do domínio.
 *
 * Optional é para fluência funcional.
 * Use map, flatMap, filter, orElseGet, etc.
 *
 * Optional não substitui validação.
 * Ele só modela ausência, não garante consistência.
 */
public class Main {

    public static void main(String[] args){
        /*Optional<String> name = Optional.ofNullable("Camargo");
        System.out.println(name.orElse("N/A"));*/

        /*Optional<String> _nome = Optional.of("");
        System.out.println(_nome.get());*/

        /*Optional<Integer> length = name.map(String::length);
        System.out.println(length.orElse(0));*/

        //transformValueMap();
        //transformValueFlatMap();
        //filterValue();
        //fallBackEager();
        //fallBackLazy();
        withException();
    }

    private static Optional<User> findUser(){
        //return Optional.of(new User("Camargo", Optional.of("linkcamargo@gmail.com")));
        return Optional.empty();
    }

    private static Supplier<String> findDefaultValue() {
        Supplier<String> sup = () -> "default";
        return sup;
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

    /**
     * Mantém o valor apenas se passar no predicado.
     * filter(Predicate<T>)
     *
     * Caso de uso:
     * -Validações simples
     * -Regras de negócio booleanas
     */
    private static void filterValue(){
        Optional<User> user = findUser();
        Optional<User> startWC = user.filter(u -> u.name().startsWith("C"));
        System.out.println(startWC);
    }

    /**
     * orElse(value)
     * Retorna o valor padrão sempre, mesmo que o Optional esteja cheio.
     *
     * Cuidado:
     * Se o valor padrão for caro de calcular, ele será calculado mesmo sem necessidade.
     */
    private static void fallBackEager(){
        Optional<User> user = findUser();
        Optional<String> email = user.flatMap(User::email);
        System.out.println(email.orElse("xiii"));
    }

    /**
     * orElseGet(Supplier<T>)
     * Retorna o valor padrão preguiçosamente.
     *
     * Caso de uso:
     * -Valores caros de calcular
     * -Acesso a banco
     * -Chamadas externas
     *
     * “orElseGet só executa o Supplier se o Optional estiver vazio.”
     */
    private static void fallBackLazy(){
        Optional<User> user = findUser();
        Optional<String> email = user.flatMap(User::email);
        System.out.println(email.orElseGet(findDefaultValue()));
    }

    private static void withException(){
        String nome = Optional.ofNullable(null)
                .orElseThrow(() -> new IllegalArgumentException("Campo obrigatorio"));
    }


}
