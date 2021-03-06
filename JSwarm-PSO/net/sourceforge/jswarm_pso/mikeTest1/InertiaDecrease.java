package sourceforge.jswarm_pso.mikeTest1;

import sourceforge.jswarm_pso.Swarm;
import sourceforge.jswarm_pso.VariablesUpdate;

public class InertiaDecrease extends VariablesUpdate {
	boolean particleIncrement=false;
	boolean inertiaDecrease=false;
	boolean localbestFix=false;

	public InertiaDecrease(boolean inertiaDec, boolean particleInc, boolean lbestf) {
		super();
		this.inertiaDecrease = inertiaDec;
		this.particleIncrement = particleInc;
		this.localbestFix = lbestf;
	}	

	/**
	 * Update Swarm parameters here
	 * @param swarm : Swarm to update
	 */
	public void update(Swarm swarm) {
		if(inertiaDecrease){
			swarm.setInertia( 0.9 - ((0.9-0.4)*InitClassification.current_iteration)/InitClassification.numberOfIterations );
		}
		
		//swarm.setInertia(0.99 * swarm.getInertia());		
		//swarm.setGlobalIncrement(1 - ((1-0.1)*InitClassification.current_iteration)/InitClassification.numberOfIterations);
		//swarm.setParticleIncrement(0.1 + ((1-0.1)*InitClassification.current_iteration)/InitClassification.numberOfIterations);
		//swarm.setGlobalIncrement(swarm.getGlobalIncrement()+0.1);
		
		if(particleIncrement){
			swarm.setParticleIncrement(0.3 + ((0.9-0.3)*InitClassification.current_iteration)/InitClassification.numberOfIterations);			
		}
		
		if(localbestFix && InitClassification.current_iteration>=(3*InitClassification.numberOfIterations/4)){
			swarm.setNeighborhoodIncrement(1.0);
			//swarm.setNeighborhoodIncrement(0.3 + ((0.8-0.3)*InitClassification.current_iteration)/InitClassification.numberOfIterations);
		}
		
		//debug
		if(Utils.debug){
			System.out.println("Inertia: "+swarm.getInertia());
			System.out.println("Global Incr: "+swarm.getGlobalIncrement());
			System.out.println("Particle Incr: "+swarm.getParticleIncrement());
		}
	}

}
