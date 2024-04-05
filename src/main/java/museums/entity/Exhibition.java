package museums.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Exhibition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exhibitionId;
	private String nameOfExhibition;	
	private String ownerOfExhibition;
	private String typeOfExhibition;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude

	@ManyToMany(mappedBy = "exhibitions", cascade = CascadeType.PERSIST)
	private Set<Museum> museums = new HashSet<>();

}
