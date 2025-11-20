import java.util.*;

// ========== TIEMPO GEOLÓGICO ==========
abstract class TiempoGeologico {
    protected String dlNombre;
    protected int dlDuracion;
    
    public TiempoGeologico(String nombre, int duracion) {
        this.dlNombre = nombre;
        this.dlDuracion = duracion;
    }
    
    public abstract void dlMostrarInfo();
}

class Fanerozoico extends TiempoGeologico {
    public Fanerozoico() {
        super("Fanerozoico", 541);
    }
    
    @Override
    public void dlMostrarInfo() {
        System.out.println("Periodo: " + dlNombre + " (" + dlDuracion + " millones años)");
    }
}

// ========== CLASIFICACIÓN ==========
abstract class ClasificacionTaxonomica {
    protected String dlNombre;
    protected int dlNivel;
    protected List<ClasificacionTaxonomica> dlSubclases;
    
    public ClasificacionTaxonomica(String nombre, int nivel) {
        this.dlNombre = nombre;
        this.dlNivel = nivel;
        this.dlSubclases = new ArrayList<>();
    }
    
    public void dlAgregarSubclase(ClasificacionTaxonomica sub) {
        dlSubclases.add(sub);
    }
    
    public void dlImprimirJerarquia() {
        String identacion = "|" + "----".repeat(dlNivel);
        System.out.println(identacion + " " + dlNombre);
        for (ClasificacionTaxonomica sub : dlSubclases) {
            sub.dlImprimirJerarquia();
        }
    }
}

// Refactorización: En mi diagrama de clases no especifiqué estos detalles correctamente,
// por eso aquí en el código simplifiqué las clases Gnathostomata, Agnathans, Hagfishes y Lampreys
// para que solo extiendan ClasificacionTaxonomica sin métodos adicionales.
// En mi diagrama debí haber incluido métodos como dlNombreGrupo(), dlSaludo(), dlComer(), 
// pero decidí implementarlos en el código de forma simple.
class Gnathostomata extends ClasificacionTaxonomica {
    public Gnathostomata() { super("Gnathostomata", 1); }
}

class Agnathans extends ClasificacionTaxonomica {
    public Agnathans() { super("Agnathans (Sin mandíbulas)", 2); }
}

class Hagfishes extends ClasificacionTaxonomica {
    public Hagfishes() { super("Hagfishes (Peces bruja)", 3); }
}

class Lampreys extends ClasificacionTaxonomica {
    public Lampreys() { super("Lampreys (Lampreas)", 3); }
}

// ========== INTERFACES ==========
interface Alimentacion {
    String comer();
    String comer(String alimento);
}

interface Comunicacion {
    String saludar();
    String saludar(String dest);
}

// ========== ANIMALES ==========
// Refactorización: En mi diagrama de clases dibujé una clase "Abstracts" que debía implementar
// las interfaces Alimentacion y Comunicacion, pero aquí en el código decidí llamarla "Animal"
// para que sea más claro y directa. Tiene la misma funcionalidad pero cambié el nombre.
abstract class Animal implements Alimentacion, Comunicacion {
    protected String dlNombre;
    protected String dlGenero;
    
    public Animal(String nombre, String genero) {
        this.dlNombre = nombre;
        this.dlGenero = genero;
    }
    
    public void dlMostrar() {
        System.out.println("Animal: " + dlNombre + " | Género: " + dlGenero);
    }
}

class Hagfish extends Animal {
    public Hagfish(String nombre, String genero) {
        super(nombre, genero);
    }
    
    @Override
    public String comer() {
        return dlGenero.equals("mujer") ? 
            dlNombre + " devorando carroña" : 
            dlNombre + " alimentAndose";
    }
    
    @Override
    public String comer(String carronia) {
        return dlGenero.equals("mujer") ? 
            dlNombre + " comiendo " + carronia : 
            dlNombre + " devorando " + carronia;
    }
    
    @Override
    public String saludar() {
        return dlGenero.equals("mujer") ? 
            dlNombre + " moviendo cola" : 
            dlNombre + " ondulando cuerpo";
    }
    
    @Override
    public String saludar(String dest) {
        return dlNombre + " saludando a " + dest;
    }
}

class Lamprey extends Animal {
    public Lamprey(String nombre, String genero) {
        super(nombre, genero);
    }
    
    @Override
    public String comer() {
        return dlGenero.equals("mujer") ? 
            dlNombre + " succionando sangre" : 
            dlNombre + " alimentandose de fluidos";
    }
    
    @Override
    public String comer(String huesped) {
        return dlNombre + " alimentandose de " + huesped;
    }
    
    @Override
    public String saludar() {
        return dlGenero.equals("mujer") ? 
            dlNombre + " saludando a todos" : 
            dlNombre + " nadando en círculos";
    }
    
    @Override
    public String saludar(String dest) {
        return dlNombre + " saludando a " + dest;
    }
}

class TanqueMutacion {
    private final int dlUmbral = 876;
    private final Random dlRandom = new Random();
    
    // Refactorización: En mi diagrama este método
    //  se llamaba dlMutarEspecie(),
    //  pero aquí lo dejé como dlMutar() para no complicarlo.
    //  La idea es la misma que pensé desde el inicio: 
    // revisar si la radiación pasa el umbral y luego mostrar qué mutación ocurrió
    
    public void dlMutar(Animal animal, String apellido) {
        int radiacion = dlRandom.nextInt(901) + 100;
        
        System.out.println("\n=== MUTACIÓN CON IA ===");
        System.out.println("Espécimen: " + animal.dlNombre);
        System.out.println("Radiación: " + radiacion + " mSv");
        
        dlMostrarProgreso(radiacion);
        
        if (radiacion <= dlUmbral) {
            System.out.println(" MUTACIÓN EXITOSA");
            System.out.println("Nueva especie: " + animal.dlNombre + apellido);
            System.out.println("|*|--- " + animal.dlNombre);
            System.out.println("|*|--- " + animal.dlNombre + apellido);
        } else {
            System.out.println("MUTACIÓN FALLIDA - Radiación excesiva");
        }
    }
    
    private void dlMostrarProgreso(int rad) {
        System.out.print("Progreso: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(i < (rad * 20 / 1000) ? "█" : "_");
        }
        System.out.println(" " + rad + " mSv");
    }
}

// ========== ANIMACIÓN ==========
// Refactorización: En mi diagrama esta clase tenía un atributo #dlVerificador que aquí no incluí
// porque decidí pasar el dígito directamente como parámetro al método dlSimular(). Esto hace
// el código más flexible sin cambiar la funcionalidad que puse en la hoja.
class AnimacionCarga {
    // Refactorización: Aquí agregué la parte de dlSimular()
    //  porque no lo agregué en la hoja, esto sirve para simular la 
    // carga o espera según si el dígito es par o impar
    public void dlSimular(int digito) {
        System.out.println("\n Procesando...");
        
        if (digito % 2 != 0) {
            // Waiting para impar
            for (int i = 0; i <= 100; i += 20) {
                char c = (i % 40 == 0) ? 'o' : 'O';
                System.out.print("\r" + c + " Esperando: " + i + "%");
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
            System.out.println("\n✓ Completado (Waiting oOo)");
        }
    }
}

// ========== PAQUETE ==========
// Refactorización: En mi diagrama de clases tenía métodos dlPaquete, dlCompleto() y dlMostrar(),
// pero aquí simplifiqué: el constructor hace lo que dlPaquete(), no implementé dlCompleto() porque
// no era necesario para la funcionalidad, y mantuve dlMostrar() como lo diseñé. También agregué la parte de 
// dlAgregar() que no estaba en mi diagrama de la hoja pero lo necesito para añadir animales.
class Paquete {
    private final String dlNombre;
    private final List<Animal> dlAnimales = new ArrayList<>();
    
    public Paquete(String nombre) {
        this.dlNombre = nombre;
    }
    
    public void dlAgregar(Animal a) {
        dlAnimales.add(a);
    }
    
    public void dlMostrar() {
        System.out.println("\n=== PAQUETE: " + dlNombre + " ===");
        for (Animal a : dlAnimales) a.dlMostrar();
    }
}

// ========== ÁRBOL ==========
// Refactorización: En mi diagrama puse que los atributos debían ser -dlPrincipal y métodos
// dlCompletar() y dlMostrar(). Aquí usé dlRaiz en lugar de dlPrincipal (más descriptivo),
// y cambié dlCompletar() por dlConstruir() porque describe mejor la acción de construir
// el árbol filogenético.

class ArbolFilogenetico {
    private ClasificacionTaxonomica dlRaiz;
    
    public void dlConstruir() {
        dlRaiz = new Gnathostomata();
        Agnathans agnathans = new Agnathans();
        dlRaiz.dlAgregarSubclase(agnathans);
        agnathans.dlAgregarSubclase(new Hagfishes());
        agnathans.dlAgregarSubclase(new Lampreys());
    }
    
    public void dlMostrar() {
        System.out.println("\n========== ARBOL FILOGENETICO ==========");
        dlRaiz.dlImprimirJerarquia();
        System.out.println("========================================");
    }
}

// ========== MAIN ==========
// Refactorización: En mi diagrama de calse donde aquí en el código simplifiqué ese flujo porque
// esos nombres eran más conceptuales. Implementé la secuencia básica: entrada de datos,
// creación del árbol, manejo del paquete, polimorfismo, animación y mutación, que era lo
// importante de lo que diseñé.
public class BiotechSystem {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println(" BIOTECH-2K25 - AGNATHANS");
        
        System.out.print("\nCedula: ");
        String cedula = sc.nextLine();
        int digito = Integer.parseInt(cedula.substring(cedula.length() - 1));
        
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        
        System.out.println("\n Grupo: AGNATHANS (dígito " + digito + ")");
        
        // Crear árbol
        Fanerozoico periodo = new Fanerozoico();
        periodo.dlMostrarInfo();
        
        ArbolFilogenetico arbol = new ArbolFilogenetico();
        arbol.dlConstruir();
        arbol.dlMostrar();
        
        // Crear paquete con animales
        Paquete paquete = new Paquete("Agnathans");
        Hagfish pepita = new Hagfish("Pepita", "mujer");
        Lamprey pedro = new Lamprey("Pedro", "hombre");
        
        paquete.dlAgregar(pepita);
        paquete.dlAgregar(pedro);
        paquete.dlMostrar();
        
        // Polimorfismo
        System.out.println("\n=== POLIMORFISMO ===");
        System.out.println("--- Mujer (Pepita) ---");
        System.out.println(pepita.comer());
        System.out.println(pepita.comer("pez muerto"));
        
        System.out.println("\n--- Hombre (Pedro) ---");
        System.out.println(pedro.saludar());
        System.out.println(pedro.saludar("Danny"));
        
        // Animación
        AnimacionCarga anim = new AnimacionCarga();
        anim.dlSimular(digito);
        
        // Mutación
        TanqueMutacion tanque = new TanqueMutacion();
        tanque.dlMutar(pepita, apellido);
        
        System.out.println("\n Sistema finalizado\n");
        sc.close();
    }
}
