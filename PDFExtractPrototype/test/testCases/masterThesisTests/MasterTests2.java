package testCases.masterThesisTests;

import static org.junit.Assert.*;
import main.MetadataStorer;

import org.junit.Test;

public class MasterTests2 {

	@Test
	public void test1() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("CLUSTERING	INCOMPLETE	ORDINAL	DATA");
		String[] author = {"XiaoBin Sun"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof Gill Dobbie and Dr Shafiq Alam");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer	Science");
		tester.setAbstractx("The issue of missing data is a common problem for researchers and data analysts working with surveys and other types of questionnaires that use ordinal data. Despite the frequent occurrence and the relevance of this missing data problem, many machine learning algorithms handle missing data in a rather naive way. The standard approach involves first imputing the missing values, and then giving the completed imputed data to the learning algorithm. One advantage of this approach is that it allows the user to select the most suitable imputation method for different datasets. However, the classification result is not promising. Su et al. proposed an algorithm called “Classifier-based Nominal Imputation” (CNI), which improves the classification problem for machine learning algorithms on incomplete nominal datasets, but the performance on ordinal data remains unknown. Our work applied this CNI technique to ordinal data and the experimental results showed that using this CNI algorithm to pre-process the incomplete ordinal dataset, resulted in significantly higher classification accuracy than learners that do not apply any imputation method and those using baseline imputation techniques, such as the most common value imputation. This CNI algorithm is found to be helpful for many learners such as K Nearest Neighbour, Naive Bayes and Multilayer Perceptron Neural Networks on incomplete ordinal data.");
		
		fail("Not yet implemented");
	}
	
	@Test
	public void test2() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Linguistic Models, Zipf’s Law, and Textual Stratification");
		String[] author = {"Michael K. R. Fowler"};
		tester.setAuthors(author);
		tester.setSupervisors("Professor Cristian S. Calude");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("In this thesis, we consider the strength of evidence for Zipf’s law in language. Historically, the statistical approaches taken to examine this have not generally been appropriate to the models proposed, although recent work has begun to change this. In Chapter 1, we provide some basic concepts on which this work relies. In Chapter 2, we examine Zipf’s law in comparison to the mixed and interspersed geometric models, by means of likelihood ratios. This is a theory-heavy approach, and illustrates that, even if Zipf’s law cannot be entirely dismissed, there is definite evidence that it fails to properly model real linguistic data. In Chapter 3, we apply significantly simpler indicators to both real linguistic data and ‘monkey models’, in which texts are produced by the random generation of characters. We observe here evidence that these models, which have occasionally been used as evidence against the Zipf distributions, do not reasonably model linguistic data, and additionally find a depth of behaviour suggesting that claims about linguistic samples as a whole should be considered with great caution. In Chapter 4, we examine some of the theoretical justification for Zipf’s law. Although we can see some linguistic influence here, the arguments are strained, and do not rise to the level where we can readily accept them without significant further work.");
		
		fail("Not yet implemented");
	}
	
	@Test
	public void test3() {
		//00018whole.pdf has font errors/poorly made pdf.
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("");
		String[] author = {""};
		tester.setAuthors(author);
		tester.setSupervisors("");
		tester.setDegree("");
		tester.setDegreeDiscp("");
		tester.setAbstractx("");
		fail("Not yet implemented");
	}
	
	@Test
	public void test4() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("On the Semantics of Uncertain Data in Possibilistic SQL Tables: Reasoning and Armstrong Samples");
		String[] author = {"Ilya Litvinenko"};
		tester.setAuthors(author);
		tester.setSupervisors("Associate Professor Sebastian Link");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Relational database management systems have been developed for applications with certain data, such as inventory, payroll and airline reservation. New applications, such as financial risk assessment, information extraction, information integration, sensor data, and scientific data management, require database systems to handle uncertainty in data. A popular qualitative approach to uncertain data is based on possibility theory. In comparison to probability theory, which is the quantitative counterpart, possibilistic data management is expected to make uncertain data elicitation easier and uncertain data processing computationally more efficient. Recently, new classes of possibilistic database constraints have been proposed that make it possible to apply classical database schema design techniques to qualitatively uncertain data. These new classes of constraints apply to pure relations and are not compliant with the requirements of the industry-standard SQL. The first contribution of my research is a possibilistic SQL data model which combines the features of both the traditional SQL data model and the possibilistic data model mentioned above. As a second contribution, I propose a semantics for possibilistic SQL keys, possibilistic SQL functional dependencies and possibilistic NOT NULL constraints. This helps database designers enforce important domain semantics within industry-standard compliant database management systems that can accommodate uncertainty in data. In a third contribution, I establish axiomatic and linear-time algorithmic characterizations of the core reasoning problem for the combined class of these constraints, namely the implication problem. This contribution has important applications in database updates and query processing. As a fourth contribution, I establish structural and computational properties of possibilistic SQL tables that are Armstrong for any set of standard constraints from the combined class. Armstrong tables help database designers identify those constraints which are meaningful for the application domain at hand. The correct and complete identification of such constraints is of utmost importance in data management. As a fifth contribution, I present the graphical user interface of a prototype system that implements my algorithm for computing Armstrong tables. This contribution forms the foundation for transferring the theoretical aspects of my dissertation into database practice. As the final contribution, I report on detailed experiments with the algorithm for computing Armstrong tables as well as its optimizations. The results of the experiments provide insight into the best-, worst-, and average-case space and time complexity of the algorithms, the impact of the NOT NULL constraints and number of available possibilisty/certainty degrees, and the time savings that the optimizations achieve. The results of my research enable database users enforce important application domain semantics within database systems that support modern requirements of industry-standard compliance and uncertainty in data. When using such modern database systems, my results can help with the design of better database schemata, processing data more efficiently, making data-driven decisions more effectively, and saving costs for cleaning data in applications. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test5() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Computing Obstruction Sets for Almost Outerplanar Graphs");
		String[] author = {"David Elliott"};
		tester.setAuthors(author);
		tester.setSupervisors("Michael Dinneen");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("Focusing on the Outerplanar graph family, we present relevant methodologies and known techniques for computing finite obstruction sets under a well-quasi-ordering. We generalise Outerplanarity to k-Apex-Outerplanar, and give a finite state congruence as well as a proven finite state membership algorithm for the k = 1 case, with a lower bound than was previously known. Later we describe our automated search for the Apex-Outerplanar graphs, where we find the full list of obstructions for the first time using this method.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test6() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Visualization of Routing Changes through Traceroute Responses");
		String[] author = {"Tobey Sheng-Yen Hung"};
		tester.setAuthors(author);
		tester.setSupervisors("Assoc-Prof Nevil Brownlee");
		tester.setDegree("Master in Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("The study of traceroute data is a well-established topic in the field of Networking, there are numerous research that utilize traceroute responses to form network topologies, providing us with a deeper insight on the routing behaviours and the properties of large networks. In this thesis, we study traceroute data obtained from actively measuring probes over 1,000 vantage points in the RIPE Atlas project. From the datasets, we produce visualizations of probes exhibiting sudden routing changes using a new set of probe distance algorithms and clustering techniques. To evaluate the effectiveness of our approach, we then compare the resulting plots with the those produced using existing techniques by Brownlee [1]. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test7() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Assuring Social and Governmental Identities for Research and Education Federations");
		String[] author = {"Jason (Xiao) Li"};
		tester.setAuthors(author);
		tester.setSupervisors("Professor Clark Thomborson");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("This thesis describes an Identity Assurance Framework that defines how social and governmental identities can be used in identity federations within the Higher Education and Research sector. We see a huge potential for the use of social and governmental identities in this sector, but only a few implementations with very limited functionality. We survey existing identity federations and their respective Identity Assurance Frameworks to identify obstacles. To overcome these obstacles, we apply a system modelling technique when designing a comprehensive Identity Federation model which supports social and governmental identities. Based on our model, we re-engineer existing Identity Assurance Frameworks to meet the new assurance requirements. Finally, we discuss the feedback we obtain on our Framework, from the IT Services department of our University, with respect to its usefulness in meeting organizational challenges in the use of social and governmental identities");
		fail("Not yet implemented");
	}
	
	@Test
	public void test8() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Improving the Performance of Near Real-Time Data Warehouses");
		String[] author = {"Zhe Wang"};
		tester.setAuthors(author);
		tester.setSupervisors("Professor Gillian Dobbie and Dr. Gerald Weber");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("A data warehouse is a database, which is used for data analysis and reporting purposes. When a data warehouse is used in business applications, a requirement is that data is processed and refreshed in a timely fashion. Typically, data warehouses are updated in a batch fashion. But the delay in this off-line update can not meet the high demand of real-time updating. Active data warehousing is considered to be an alternative to the traditional data warehousing mechanism. It provides a better way to process real-time data in order to achieve a higher consistency between the stored information and the latest data updates. One of the key components of performing real-time data integration is the join between the incoming data stream and a disk relation. There is a heated discussion about the trade-off between the throughput and memory usage in streambased join algorithms. Many efforts have been made in the stream-based join research area to figure out the best practice. R-MESHJOIN is a novel and improved streambased join algorithm, which could tune the memory allocation of key components. On the one hand, R-MESHJOIN removes the dependency between the number of iterations to bring the disk relation into memory and the size of partitions in an internal queue of the stream data. On the other hand, the R-MESHJOIN algorithm is based on equi-join, which means the join performed is based on join attribute equality. In some scenarios, the stream-based join is not limited to equi-join. Similarity join (also known as non-equi join) is also required. Clearly, an improved algorithm is needed for handling similar scenarios. This thesis proposes a trie-based data structure for performing non-equi stream-based joins. To handle the similarity join scenario and store the matching attribute, we build an ordered tree data structure based on a trie. The join attribute is stored in the trie. The trie-based similarity join structure is integrated with R-MESHJOIN. To perform a non-equi join based on R-MESHJOIN, the algorithm traverses the trie to complete the fuzzy match with the join attribute and picks up the corresponding tuple as a result. A prototype of the trie-based data structure and integrated R-MESHJOIN algorithm have been implemented and the overheads of the algorithm are measured. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test9() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("State Complexity of Finite State Automata");
		String[] author = {"Nan Ke"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof. Bakhadyr Khoussainov");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("We study the subset construction that transforms nondeterministic finite automata (NFA) to deterministic finite automata (DFA). It is well known that given a n-state NFA, the subset construction algorithm produces a 2 n -state DFA in the worst case. We provide regular languages such that given n, k (k > 1 and n > k), the minimal NFA recognizing the language has n states and the minimal DFA recognizing these languages has (k + 1)n−(k + 1)2 + 2 states. Futhermore, we show that for every n = k + m and p ∈ N + (k, m > 1 and m = 2 k+1−pk−2p p−1 ) there exists a regular language Ln, such that the minimal NFA recognizing the language has n states and the minimal DFA recognizing the language has p · n states. In addition, for every k > 1, we provide a regular language such that the minimal NFA recognizing the language has n-states, and the minimal DFA recognizing the language has asymptotically n k states. Importantly, the minimal NFA has O( n 2 log2n ) number of transitions, which is fewer than the known results in literature [8, 9]. We also investigate the question of the complementation of NFA. We provide regular languages such that given n, k (k > 1 and n > k), the NFA recognizing these languages need n states and the minimal NFA recognizing their complement has (k + 1)n − (k + 1)2 + 2 states. Finally we show that for given n, k > 1, there exists an O(n)-state NFA A such that the minimal NFA recognizing the complement of L(A) has between O(n k−1 ) and O(n 2k ) states. Importantly, the constructed NFA have a small number of transitions, i.e., between O(n) or O(n 2/log2(n)). These are better than the comparable results in literature [7, 9]. Furthermore, we study properties of random automata. We consruct random automata by assigning transitions between any ordered pair of states with a fixed probability. We examine with which probability a random automaton is deterministic, which languages are accepted by most automata, and the probability of a random automaton having exponential blow-up upon determinization.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test10() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Computing Obstruction Sets");
		String[] author = {"Ralph Bernard Versteegen"};
		tester.setAuthors(author);
		tester.setSupervisors("Dr. Michael J. Dinneen and Prof. Marston Conder");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("This thesis focuses on extending the scope of practically computable obstruction sets. An obstruction set under a graph ordering  for a family of graphs F is a characterisation of the family in terms of containment: a graph G is not in the family if and only if there is some obstruction H such that H  G. The prototype is Kuratowski’s Theorem. We describe how existing algorithms can be extended to compute obstruction sets under the immersion, Y ∆- and H ./-orders, and other practical measures to improve the feasibility of computation. This allowed us to compute for the first time the minor order obstructions for graphs with vertex cover at most 7. We then briefly outline a simple finite state algorithm for a congruence for boundaried graphs embeddable in any orientable or nonorientable closed surface, possibly with a specified number of covering faces (generalising outer-planarity) or apex vertices. Having implemented (most of) this algorithm we can compute the complete obstruction sets for these graph families given a pathwidth bound on the obstructions. We currently have no such bounds, but demonstrate the method by computing partial obstruction sets for the plane, projective plane, Klein bottle, torus and double torus. We also provide a new, shorter proof that the minor order obstruction set of the union of two ideals C1 and C2 is computable from the obstruction sets for C1 and C2.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test11() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Belief Propagation Based Stereo Matching with Due Account of Visibility Conditions");
		String[] author = {"Rui Gong"};
		tester.setAuthors(author);
		tester.setSupervisors(null);
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Belief propagation based stereo matching algorithms are explored with the main focus on taking account of visibility constraints. This approach approximates the minimum energy solution on graphical models such as Markov Chains, or Markov Random Field (MRF) of disparities. Our approach exploits a symmetric Cyclopean matching model, which accounts for visibility conditions, to construct epipolar profiles which are close to the human perception. Unlike traditional asymmetric matching models, this model can construct disparity maps with respect to the left, right or Cyclopean reference frame, as well as a Cyclopean image of a 3D scene depicted in a stereo pair, simultaneously. We focused on both one-dimensional (1D), and two-dimensional (2D) belief propagation. 1D belief propagation has the advantage of fast computation, and low memory usage, but suffers matching errors due to the lack of vertical information. This algorithm was mapped to Graphics Processing Unit (GPU) using CUDA, to achieve real-time stereo. Our research also uncovered one hidden problem of non-unique solutions. Further research into this problem could lead to new ways to improve the matching accuracy. 2D belief propagation is more memory intensive, and has slower computation speed, but it can achieve high quality results using the powerful 2D message passing, where matching information is passed around the MRF, and decisions are made using all the image information.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test12() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("A Social Network-based Information Dissemination Scheme");
		String[] author = {"Yanfeng Li"};
		tester.setAuthors(author);
		tester.setSupervisors("Dr. XinFeng Ye");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("Handheld devices, e.g. smart phones, PDA, etc., are becoming ubiquitous to peoples’ daily life. One of the features of the handheld devices is that they can be used to store information. Modern handheld devices, e.g. iPhone 4, can store gigabytes of information. Most handheld devices are capable of using the Bluetooth technology to automatically exchange information with other handheld devices that are located in close proximity. This brings an opportunity for disseminating information through the handheld devices. The advertising industry is always looking for ways to effectively deliver their advertisements to the customers who are interested in the products being advertised. Social events organizers are also looking for ways to publicize their events to make the events known by people who are interested in the events. For artists, they want to be famous by publicizing their artworks. Thus, they are looking for ways to effectively publicize their artworks to people who are interested in the artworks. Due to the wide spread use of the handheld devices and their ability to exchange information with the other devices in close proximity, it is natural to utilize the handheld devices in delivering information to the people who are interested in the information. This thesis proposes a scheme for effectively disseminating information through the handheld devices. The scheme uses social network analysis to make information reach people who are interested in the information more effectively. An incentive scheme is used to encourage people to deliver information to other people who are interested in the information. The scheme uses the integer programming technique to solve an optimized problem, to maximize people’s rewards in an incentive scheme under the condition of imited handheld device data storage. According to the decision results from integer programming, handheld devices decide what information to keep for future delivery to maximize their owners’ income. A simulator is developed to evaluate the effectiveness of the proposed scheme. Simulations show that the proposed scheme is effective in disseminating information and generating income for handheld device owners");
		fail("Not yet implemented");
	}
	
	@Test
	public void test13() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("EBM: A Quantitative Usability Model for Video Games");
		String[] author = {"Danny Ming-Hung Wei"};
		tester.setAuthors(author);
		tester.setSupervisors(null);
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Usability is an important aspect of video games, because an unusable system can prevent a player from enjoying a game. This thesis presents a quantitative model to measure game control usability of platform video games, called EBM. It is based on the hypothesis that the effort required to play a game is a good indicator of the game’s usability. EBM produces a score called “EBM score”, which estimates game control usability. It is calculated by combining the contributing factors of effort, such as duration and intuitiveness measures. The duration and intuitiveness measures of each game function are combined through a weighted average, weighted by each game function’s relative function usage. Then the EBM score is calculated by a weighted sum of the overall duration and intuitiveness measures, using factor weights. EBM was validated by measuring the correlation between EBM scores and SUS scores for a total of 33 control schemes for 7 different platform games (5 schemes for 5 games, and 4 schemes for 2 games). Two different tests were conducted to validate different aspects of the model. The first test investigated EBM’s capability of estimating game control usability of different control schemes from the same game. The second test investigated EBM’s capability of estimating game control usability of different control schemes from different games. For each control scheme 3 measurements were taken from 3 different participants. The correlations between the EBM scores and SUS scores of the 5 control schemes correlated significantly for each of the 7 games. Furthermore, the overall correlation between the EBM and SUS scores of all 33 control schemes across the 7 games was significant. Next, Controller Designer was developed to automate the calculation of EBM scores. It is based on EBM, and is used to support developers in designing control schemes. The tool was evaluated based on its usability and effectiveness in helping the developers design a usable control scheme. The evaluation process involved using a set of interview questions to measure the effectiveness of Controller Designer, and a SUS questionnaire to measure the usability of Controller Designer. The preliminary results showed that the tool contains problems that still need to be resolved before the tool can be useful.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test14() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Animated 3D Visualization of Stereo and Motion Analysis on EISATS Data");
		String[] author = {"Shuang An"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof. Reinhard Klette");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("The EISATS website offers various video data for testing stereo and motion analysis algorithms. Due to the increasing use of those data by the international computer vision community, it appeared to be of benefit to offer sources for 3D visualization of stereo an motion analysis results, especially for cases where no ground truth is available for evaluating the accuracy of results. This MSc project contributes to the task to verify how particular stereo or motion analysis algorithms perform on those sequences if only subjective means (i.e. no ground truth) are available. For this purpose, analysis results are shown in 3D space. The visualization is based on OpenGL and on specifically designed colour keys for disparity and optic flow data. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test15() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("IPv4 - IPv6 Coexistence Technique");
		String[] author = {"Se-young Yu"};
		tester.setAuthors(author);
		tester.setSupervisors("Brian Carpenter");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("An IPv4 (Internet Protocol version 4) – IPv6 (Internet Protocol version 6) coexistence technique is essential for the IPv4 to IPv6 translation period as it is expected to take a long time to deploy IPv6 to all available devices. As one of the IPv4 – IPv6 coexistence techniques, an IPv4 – IPv6 translation will allow any client connected to the IPv6-only network to communicate to an IPv4 host. However the efficiency of any IPv4 – IPv6 translator has not been recognized as an important metric to be considered even though most organizations that will make use of an IPv4 – IPv6 translator should be interested in the best translation technique based on its performance and stability. The purpose of this study is to understand the fundamental structure and behavior of IPv4 – IPv6 translators and investigate the efficiency of currently implemented open source IPv4 – IPv6 translation techniques. In this study, we have chosen two IPv4 – IPv6 translators and a HTTP proxy to compare their efficiency based on the Round-Trip time of packets against the native IPv4 and IPv6 connection. The behavior of each packet translator and HTTP proxy is observed to compare efficiency while it handles a variable number of simultaneous connections. Through this study, we will understand how each translator and HTTP proxy can be used to enable IPv4 – IPv6 communication and how efficient these translation techniques are when it is deployed within a simple network.");
		fail("Not yet implemented");
	}
}
