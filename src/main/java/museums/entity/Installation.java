package museums.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Data;

@Entity
@Data
public class Installation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long installationId;
	private String nameOfInstallation;
	private String typeOfInstallation;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "museum_id")

	private Museum museum;
}