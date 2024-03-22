package museums.entity;

import jakarta.persistence.Entity;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Data;

@Entity
@Data
public class Museum {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long museumId;
	private String museumName;
	private String museumAddress;
	private String museumCity;
	private String museumCountry;
	private String museumZip;
	private String museumPhone;
	private String museumWebsite;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "museum_exhibition", joinColums = @JoinColumn(name = "museum_id"), inverseJoinColumns = @JoinColumn(name = "exhibition_id"))
	
	private Set<Exhibition> exhibitions = new HashSet<>();
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "museum", cascade = CascadeType.ALL, orphanRemoval = true)

	private Set<Installation> installations = new HashSet<>();
}
