import java.util.*;

// ========== Hormiga =========
abstract class Hormiga {
    protected String dlNombre;
    protected String dlEstado;
    
    public Hormiga(String nombre) {
        this.dlNombre = nombre;
        this.dlEstado = "VIVA";
    }
    
    public abstract void dlComer(String alimento);
}

class HSoldado extends Hormiga {
    public HSoldado(String nombre) {
        super(nombre);
    }
    
    @Override
    public void dlComer(String alimento) {
        if (alimento.equals("Carnivoro")) {
            dlEstado = "VIVE";
            System.out.println(dlNombre + " come Carnivoro -> VIVE");
        } else {
            dlEstado = "MUERE";
            System.out.println(dlNombre + " come " + alimento + " -> MUERE");
        }
    }
}

// ========== BBA (Bomba) ==========
class BombaBBA {
    private Map<String, Map<Character, String>> dlTransiciones;
    private Set<String> dlEstadosFinales;
    
    public BombaBBA() {
        dlInicializarAutomata();
    }
    
    private void dlInicializarAutomata() {
        dlTransiciones = new HashMap<>();
        dlEstadosFinales = new HashSet<>(Arrays.asList("q1", "q6"));
        
        // q0 -> a -> q1 (a+)
        Map<Character, String> q0 = new HashMap<>();
        q0.put('a', "q1");
        q0.put('a', "q2"); // Refactorizacion: deberia ir a q2 para abcdt+
        dlTransiciones.put("q0", q0);
        
        // q1 -> a -> q1 (bucle a+)
        Map<Character, String> q1 = new HashMap<>();
        q1.put('a', "q1");
        dlTransiciones.put("q1", q1);
        
        // q2 -> b -> q3
        Map<Character, String> q2 = new HashMap<>();
        q2.put('b', "q3");
        dlTransiciones.put("q2", q2);
        
        // q3 -> c -> q4
        Map<Character, String> q3 = new HashMap<>();
        q3.put('c', "q4");
        dlTransiciones.put("q3", q3);
        
        // q4 -> d -> q5
        Map<Character, String> q4 = new HashMap<>();
        q4.put('d', "q5");
        dlTransiciones.put("q4", q4);
        
        // q5 -> t -> q6
        Map<Character, String> q5 = new HashMap<>();
        q5.put('t', "q6");
        dlTransiciones.put("q5", q5);
        
        // q6 -> t -> q6 (bucle t+)
        Map<Character, String> q6 = new HashMap<>();
        q6.put('t', "q6");
        dlTransiciones.put("q6", q6);
    }
    
    public boolean dlValidarArsenal(String cadena) {
        String estado = "q0";
        
        for (char c : cadena.toCharArray()) {
            if (!dlTransiciones.containsKey(estado)) return false;
            Map<Character, String> trans = dlTransiciones.get(estado);
            if (!trans.containsKey(c)) return false;
            estado = trans.get(c);
        }
        
        return dlEstadosFinales.contains(estado);
    }
}

// ========== Coordenada ==========
class Coordenada {
    String dlGeoposicion;
    String dlLunes;
    String dlMartes;
    String dlMiercoles;
    String dlJueves;
    String dlViernes;
    String dlTipoArsenal;
    
    public Coordenada(String geo, String l, String ma, String mi, String j, String v, String arsenal) {
        this.dlGeoposicion = geo;
        this.dlLunes = l;
        this.dlMartes = ma;
        this.dlMiercoles = mi;
        this.dlJueves = j;
        this.dlViernes = v;
        this.dlTipoArsenal = arsenal;
    }
}

// ========== AntCiberDron ==========
class AntCiberDron {
    private String dlNombre;
    private String dlCedula;
    private HSoldado dlHormiga;
    private BombaBBA dlBomba;
    
    public AntCiberDron(String nombre, String cedula) {
        this.dlNombre = nombre;
        this.dlCedula = cedula;
        this.dlHormiga = new HSoldado("Soldado-KGD");
        this.dlBomba = new BombaBBA();
    }
    
    public void dlLeerCSV(String archivo) {
        // Refactorizacion: deberia leer desde archivo real
        System.out.println("\n[+] INFORMACION:");
        System.out.println("Nombre: " + dlNombre);
        System.out.println("Cedula: " + dlCedula);
        
        System.out.println("\n[+] TIPO HORMIGA:");
        dlHormiga.dlComer("Carnivoro");
        
        List<Coordenada> coords = new ArrayList<>();
        coords.add(new Coordenada("Coord-01", "01-02", "", "", "", "", "aaaa"));
        coords.add(new Coordenada("Coord-01", "01-02", "", "", "", "", "a"));
        coords.add(new Coordenada("Coord-05", "", "", "", "", "05-10", "abcdttt"));
        coords.add(new Coordenada("Coord-05", "", "", "", "", "05-10", "abcdt"));
        
        System.out.println("\n[+] COORDENADAS:");
        for (Coordenada c : coords) {
            dlMostrarLoading();
            System.out.println("100% " + c.dlGeoposicion + " | " + c.dlTipoArsenal);
        }
        
        System.out.println("\n[+] COORDENADAS A DESTRUIR:");
        for (Coordenada c : coords) {
            if (c.dlGeoposicion.equals("Coord-01") || c.dlGeoposicion.equals("Coord-05")) {
                boolean explota = dlBomba.dlValidarArsenal(c.dlTipoArsenal);
                System.out.println(c.dlGeoposicion + " | " + c.dlTipoArsenal + " | " + explota);
            }
        }
    }
    
    private void dlMostrarLoading() {
        char[] chars = {'\\', '|', '/', '-'};
        for (int i = 0; i <= 100; i += 25) {
            System.out.print("\r" + chars[i / 25 % 4] + " " + i + "%");
            try { Thread.sleep(50); } catch (Exception e) {}
        }
        System.out.print("\r");
    }
}

// ========== Main ==========
public class LanchimbaDanny {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("       ANTDRON2K25 - KGD RUSO");
        System.out.println("========================================");
        
        AntCiberDron dron = new AntCiberDron("Danny Lanchimba", "1050149515");
        dron.dlLeerCSV("LanchimbaDanny.csv");
        
        System.out.println("\n========================================");
        System.out.println("     MISION COMPLETADA, HORMIGUITA GANADORA");
        System.out.println("========================================");
    }
}
