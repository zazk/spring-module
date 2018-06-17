package pe.gob.sernanp.pda;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_categoria")
public class    Book {
    @Id
    private int id;

    private String title;

    public String getTitle() {
        return title;
    }
}
