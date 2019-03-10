package creaturesim.logic;

import creaturesim.neural.HardlimNode;
import creaturesim.neural.SigmoidInputNode;
import creaturesim.neural.SigmoidNode;

public class Creature{
	SigmoidInputNode bearing = new SigmoidInputNode();
	SigmoidInputNode distance_to_object = new SigmoidInputNode();
	SigmoidInputNode size_of_object = new SigmoidInputNode();
	SigmoidInputNode size = new SigmoidInputNode();
	SigmoidInputNode[] inputs = new SigmoidInputNode[]{
										bearing,
										distance_to_object,
										size_of_object,
										size,
									};
	SigmoidNode h0 = new SigmoidNode(inputs);
	SigmoidNode h1 = new SigmoidNode(inputs);
	SigmoidNode h2 = new SigmoidNode(inputs);
	SigmoidNode h3 = new SigmoidNode(inputs);
	SigmoidNode[] hiddens = new SigmoidNode[]{h0, h1, h2, h3};
	HardlimNode turning = new HardlimNode(hiddens);
	HardlimNode movement = new HardlimNode(hiddens);
}
