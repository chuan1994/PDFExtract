package testCases.masterThesisTests;

import static org.junit.Assert.*;
import main.MetadataStorer;

import org.junit.Test;

public class MasterTests1 {

	@Test
	public void test1() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Animated Non-Photorealistic Rendering");
		String[] author = {"Ting-Yeh Chen"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof. Reinhard Klette");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("This thesis presents an algorithm and its application for the artistic rendering of recorded video data based on non-photorealistic applications. The proposed method does not only work on a variety of artistic rendering styles for static photography but it can also be applied for the creation of artistic videos. To date, many algorithms have been introduced in the field of non-photorealistic rendering. For example, in a work by Crystal Valente, different artistic styles were emulated on static photographs. This project extended the methods to an algorithm for obtaining animations as artistic videos. Cartoon-like and comic-like styles are the two artistic styles introduced in this thesis. The goal in this project is to create animations in these two artistic styles. For creating successfully an artistic video, three key challenges are addressed: temporal (colour) consistency, stylistic flexibility, and scalability. Within this project, the main focus in video processing is on temporal consistency between consecutive frames. This leads to an improvement in visual quality. Stylistic flexibility aims at allowing users to experiment with different artistic styles to meet aesthetic requirements or individual preferences. Scalability is to control the time performance of the involved processes. Our work on addressing these challenges started with collecting image data, i.e. videos and photographs. Then we designed a method for video-based nonphotorealistic rendering from those input image data, either in cartoon-like or in comics-like style. The thesis demonstrates the benefit of the designed video-based rendering framework by comparing its results with results obtained from existing Android apps.");
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void test2() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Mobile Stereo Vision Process Performance Assessment: Local versus Remote Processing");
		String[] author = {"Qingnan Liu"};
		tester.setAuthors(author);
		tester.setSupervisors("professor Patrice Delmas");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("Stereo vision processing is relatively resource intensive in order to produce a reasonable good 3D result. This processing requires powerful CPUs and GPUs to render a collection of original images using complicated algorithms. Only powerful servers are capable of completing this task in short period of time. Original images and processed results need to be transferred between client and servers. A slow network connection may counteract the advantage of server processing speed. Recent evolution of microprocessor technology gives an opportunity to embed powerful CPUs and GPUs into portable devices. So a powerful smart phone processor is capable of completing the stereo vision processing. Although this processing still needs a longer time to execute on portable devices than on servers, original images and results do not necessarily needed to transfer through network.");
		
		fail("Not yet implemented");
	}
	
	@Test
	public void test3() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Iterative Design and Fabrication of Tangible Objects.");
		String[] author = {"Brent Milton Whiteley"};
		tester.setAuthors(author);
		tester.setSupervisors("Beryl and Rachel");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("The objective of this research is to create a prototype system for the iterative design and fabrication of physical 3D models using tangible controls. Current approaches to 3D modelling can be difficult to use. To construct 3D models, complex software is used that requires a long training time to use effectively. In addition, mouse and keyboard interaction is unnatural and is inconsistent with how we interact with the 3D real world. Tangible User Interfaces provide physical controls for input into the computer. These physical 3D objects known as tangibles allow the use of natural interaction with the virtual system, which assists in making the software easier to use. This research seeks to emulate the natural interaction between humans and building blocks such as Lego, while still providing the benefits of a virtual environment. We construct a prototype system, Tangible Tango, to achieve the research objective. This system uses a table-top computer with tangible and multi-touch interaction to create 3D models for a 3D printer. New models are constructed by placing the physical models next to each other on the table-top and prompting the system to meld the virtual representation. The virtual models can then be fabricated using the 3D printer and are assigned an identification tag which is attached to the physical model. When the tagged tangible is placed on the table-top the tag is used to retrieve the matching virtual model. The ability to use the results from the system as new tangibles creates a dynamic set of tangibles available to the system. The tangible modelling interaction is supported by software features such as snapping and the functionality to import new models into the system. To evaluate the potential of the prototype and this style of interaction, a user study was performed. The study found that all participants efficiently produced the desired results, regardless of background. Participants found the tasks easy to understand and the system easy to use. Every participant had an opportunity to design, fabricate and reuse their own model. Every participant managed to successfully complete this task and reported a high level of satisfaction with the results. This indicates that the system is easy to learn and takes us one step closer to melding tangible and virtual 3D models.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test4() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Extracting Human Silhouettes from Video Sequences");
		String[] author = {"Zhengping Wang"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof. Reinhard Klette and Dr. Radu Nicolescu");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Extracting human silhouettes from video sequences is often a first step for highlevel vision analysis tasks, such as video surveillance, identification, people tracking, or activity recognition. Although there is numerous research on how to detect human silhouettes in video sequences, there are still challenges in the field due to background local motion, moving shadows, or changes in lighting conditions. This thesis presents a new robust human silhouette extraction system. In our system we proposed a shadow detection method for silhouette extraction of a person in grey-level video sequences. We use a shadow evaluator to verify whether a candidate shadow pixel, detected by Gaussian distribution analysis, is actually confirmed to belong to a shadow region. The evaluator considers a candidate shadow pixel initially as being a false-positive, and marks it as a silhouette pixel if it is enclosed or semi-enclosed by moving occlusion boundaries of a person. Those boundaries were extracted by subtracting edges in the current frame from edges of the background. We also propose a silhouette-compensation technique to recover some missing (i.e. removed) silhouette pixels by using a similarity criterion between silhouette pixels and their neighbours. The thesis also proposes a silhouette tracking algorithm based on a silhouette feature vector. Experimental results show that the proposed shadow detection method detects a silhouette of a person more accuratly compared to other methods. Methods suggested by other researchers in YUV or RGB colour space typically identify silhouette pixels incorrectly as being in a shadow if the colour of these pixels is similar to that of the surrounding background. The effectiveness of our proposed compensation algorithm and of our suggested silhouette tracking method are confirmed by experimental results. There can be various applications of the proposed techniques such as 3D human pose understanding for evaluating the performance of body movements (e.g. for disabled children, or for sportsmen), up to artistic video rendering (e.g. when replacing a moving silhouette by a “ghost effect”). The work on this thesis led to two publications at international conferences, see [1] and [2].");
		fail("Not yet implemented");
	}
	
	@Test
	public void test5() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Frequent Graph Mining for Data Streams");
		String[] author = {"Ranran Bian"};
		tester.setAuthors(author);
		tester.setSupervisors("Dr. Yun Sing Koh and Professor Gillian Dobbie");
		tester.setDegree("Master in Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Frequent graph mining is a challenging task that extracts novel and useful knowledge from graph data. The problem becomes even more challenging when the information comes from data streams which evolve in real-time. Additionally, concept drift detection is needed for the problem domain. In this thesis we present an approach for adaptively mining frequent graph patterns on time-varying streams. Our approach extends an existing graph batch mining framework by implementing and integrating a state-of-the-art change detector. The approach works on coresets of closed frequent subgraphs, compressed representations of graph sets and uses the change detector to address potential concept drifts. In our approach, we mine and monitor the concept drifts of the coresets of closed frequent subgraphs. An evaluation study on large scale datasets compares the performance between our approach and a change-adaptive algorithm in the existing graph batch mining framework. The experiments process different real-world chemical molecular and social network graph datasets with varying severity of artificial drifts. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test6() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("A Large Scale Data Migration to Integrated GIS Applications With Highly Constrained Timeframe and Resources");
		String[] author = {"Laya Zarif Soltani"};
		tester.setAuthors(author);
		tester.setSupervisors(null);
		tester.setDegree(null);
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Legacy information systems (LIS) are the main strength of organizations’ work flow. Therefore they are critical and their failure can have serious impacts on business [1]. A legacy information system is a massive investment which represents long term data capture. However an old LIS can cause several problems for the host. The final solution for LIS problems is data migration to the new system. Data migration is very complex but it can bring long term benefits to the organization. Although there is not a single generic method to suit all types of systems, it is essential to have sets comprehensive guidelines [1]. According to the participant data migration surveys, large percentages of the data migration projects last longer than anticipated. Consequently, resources are used for longer than planned which causes the risk of the budget overrun. Careful and detailed planning needs to take place to limit the overtime risks and to minimize the data loss. Any data interruption could have a severe negative public relations impact. The loss or unavailability of the critical business data could directly impact the customer service and profit and loss of the business[2]. Time and budget are the two main elements or risk factors of the data migration [2]. There is no doubt that highly constrained budget and timeframe have a big impact on the whole data migration process and can cause sacrifice of some of the data migration steps. The main purpose of this work is to propose a detailed process model for large scale data migration projects for the integrated GIS target system, while coping with highly constrained timeframe and budget which are lacking from the previous studies. This is achieved by minimizing some of the general migration steps according to their risk analysis. In order to validate the process proposal, the proposed data migration process is applied to the newly developed integrated GIS application from a series of different legacy systems.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test7() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("NEUROEVOLUTION FOR MICROMANAGEMENT IN REAL-TIME STRATEGY GAMES");
		String[] author = {"Shunjie Zhen"};
		tester.setAuthors(author);
		tester.setSupervisors("Dr. Ian Watson");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("Real-Time Strategy (RTS) games have become an attractive domain for Artificial Intelligence research in recent years, due to their dynamic, multi-agent and multi-objective environments. Micromanagement, a core component of many RTS games, involves the control of multiple agents to accomplish goals that require fast, real time assessment and reaction. This thesis explores the novel application and evaluation of a Neuroevolution technique for evolving micromanagement agents in the RTS game StarCraft: Brood War. Its overall aim is to contribute to the development of an AI capable of executing expert human strategy in a complex real-time domain. The NeuroEvolution of Augmenting Topologies (NEAT) algorithm, both in its standard form and its realtime variant (rtNEAT) were successfully adapted to the micromanagement task. Several problem models and network designs were considered, resulting in two implemented agent models. Subsequent evaluations were performed on the two models and on the two NEAT algorithm variants, against traditional, non-adaptive AI. Overall results suggest that NEAT is successful at generating dynamic AI capable of defeating standard deterministic AI. Analysis of each algorithm and agent models identified further differences in task performance and learning rate. The behaviours of these models and algorithms as well as the effectiveness of NEAT were then thoroughly elaborated");
		fail("Not yet implemented");
	}
	
	@Test
	public void test8() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Validation of Application Semantics with XML Schema");
		String[] author = {"Bo LIU"};
		tester.setAuthors(author);
		tester.setSupervisors(" Associate Professor Sebastian Link");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("The eXtensible Markup Language (XML) has evolved to become the de facto industry standard for the sharing and integration of data. This is mainly due to the syntactic flexibility by which end users can produce XML documents. Unfortunately, this flexibility is an inhibitor when it comes to the validation of XML documents with respect to application semantics. XML schema languages such as document type definitions and the standard XML Schema provide rich constructs to impose many structural constraints, but have significant shortcomings when it comes to semantics. Keys, as proposed by XML Schema, are not naturally defined and show provably bad computational properties. The research literature has proposed alternative key languages which are reminiscent of keys from databases, do not require an XML Schema definition, and have provably good computational properties. So far, the validation of the alternative key languages has not been studied in the research literature. It is neither obvious how to specify the semantics of such keys and how efficient a potential validation of such semantics would be. The first main contribution of this thesis is to express keys, as proposed by Buneman et al. [10, 12], in XQuery. It was found that XQuery provides features that can express complex key semantics in a logically structured and simple way. It is stressed that the implementation is capable of expressing XML keys beyond the path languages proposed by Buneman et al. [10, 12]. An example is given of how to validate the XML keys as assertions in XML Schema 1.1, too. Note that assertions are a key feature of XML Schema 1.1, not available in version 1.0. The second contribution of this research is to analyse the time-complexity of the validation process. The findings show that the validation works efficiently for rather complex XML keys on modestly-sized documents. However, the price for our natural implementation of XML keys is that the validation does not appear to scale well to larger XML documents. For such documents, it would be useful to invest future research into more complex implementations that scale better. The results in this thesis contribute to an increase in the semantic validity of XML documents, fewer flaws in the integration and exchange of documents, better decision-making capabilities based on less invalid data, and lower costs for data cleaning.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test9() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Learning Conjunctive SQL Queries by Example");
		String[] author = {"Lin Zhang"};
		tester.setAuthors(author);
		tester.setSupervisors("Sebastian Link");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("The Structured Query Language (SQL) has been the ANSI and ISO industry standard for defining and querying databases for more than three decades now. SQL still dominates the market and influences new evolving paradigms. The increasingly central role of data in business, science and society brings forward a new wave of database users that need to learn SQL to become or remain successful in their domain. Querying databases is a non-trivial exercise: Queries must be specified with respect to a schema which might be difficult to comprehend or not even available, and there is generally no feedback whether the query that users write is actually sound in terms of its semantics. Many new users cannot afford to attend courses to learn how to write sound queries, and even those who can, may still remain in doubt whether they have written a query that meets the target. Trainers of SQL may find it impossible to provide detailed feedback on the soundness of a trainee’s query. This is particularly challenging as there are many different ways by which the semantics of a query can be expressed syntactically in SQL. As humans learn a lot from good examples, ideally, users would like to see some example that illustrates the difference of their query from the correct one. Exploiting randomly generated databases or real-world databases for that purpose is doomed: one cannot expect for an SQL query Q to simply find a database db which produces different answers than Q for every SQL query Q′ that is semantically different from Q. Not surprisingly, previous research on this topic has identified severe limits to this approach. Nevertheless, in terms of learning how to write semantically sound SQL queries it would be great to have an algorithm that could somehow magically produce, for a given query Q, a database dbQ that produces answers to Q that are different from answers to any query Q′ that is semantically different from Q. It is widely accepted that most queries that database users ask in practice are conjunctive queries. This research has established foundations as well as an implementation and performance analysis of an algorithm that computes such databases dbQ for conjunctive SQL queries. More precisely, for each conjunctive SQL query Q the algorithm computes a database dbQ such that for all queries Q′ ∈ L(Q), Q and Q′ are semantically equivalent if and only if the evaluation of Q on dbQ produces the same answers as the evaluation of Q′ on dbQ. Here, L(Q) consists of all those conjunctive SQL queries that have the same SELECT and FROM clause as Q. A database dbQ with these properties is called an L(Q)-test database for Q. Having dbQ, reduces the query equivalence problem for the class L(Q) to the evaluation of such queries on dbQ. It is therefore not surprising that the existence and computation of test databases are non-trivial. Indeed, it is known that there are conjunctive queries under set semantics for which no test databases exist. It is shown that that the investigation of conjunctive queries under bag semantics, i.e. conjunctive SQL queries, is therefore a key enabler for the general existence of test databases. The design of the algorithm for computing a test database for Q is based on identifying tuples that represent Q and some queries that properly contain Q and are minimal with that property. The algorithm is implemented in form of a Graphical User Interface (GUI) that shows the results of both target query Q and user query Q′ ∈ L(Q), and highlights any existing differences in the results to help users understand either why their query is incorrect or assure them that they produced a correct answer. The GUI therefore provides considerable assistance in learning conjunctive SQL queries. In addition to developing the algorithm that computes dbQ and its implementation in form of the GUI, the research also conducts several experiments that evaluate the performance of the algorithm and analyzes the results. The experiments confirm the intuition that the computation of L(Q)-test databases for conjunctive queries is efficient in practice");
		fail("Not yet implemented");
	}
	
	@Test
	public void test10() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Vehicle Detection in Monocular Night-Time Grey-Level Videos");
		String[] author = {"Umesh Kumar"};
		tester.setAuthors(author);
		tester.setSupervisors("Prof. Reinhard Klette");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Road traffic accidents have been a huge problem for many decades. Ongoing efforts aim at the development of systems that can help to minimize the number of fatal accidents by providing warnings to the driver. These systems are known as Vision-Based Driver Assistance Systems. There have been many inventions since the 1980’s to make driver assistance systems more reliable. Many researchers contributed their efforts to making such systems more accurate. Driving under any conditions involves many risks on busy roads. Countless means have been proposed to reduce the risks and prevent accidents. One such approach is motion detection of nearby moving vehicles using different image analysis algorithms. Vision-based driver assistance systems are an amalgamation of technology, software programs, hardware, and GPS. The number of vehicles on roads increases every year. Unfortunately, the numbers of accidents are also proportional to this increase. There have been many safety measures undertaken by the joint collaboration between researchers and the automotive industry to include vision-based systems and increase road safety. Vehicle detection at night-time is more difficult than detection at day-time due to the limited availability of features and more challenging lighting conditions. When driving at night-time, vehicles approaching from the front are only visible by their headlights. This thesis presents a night-time vehicle detection system based on the processing of gray-level video. An automatic threshold step is introduced, which reduces noise caused by non-candidate objects (such as street lights, traffic lights, road sign boards and so forth). Regions of interest (RoI) are identified by training a classifier for this task. In the described experiments, RoIs are headlights of approaching vehicles from the front. A large dataset has been used to train the classifier. The thesis proposes a specific technique to ensure time-efficiency when training the classifier. Experiments evaluate a monocular vision system capable of detecting vehicles approaching from the front. The proposed system uses a Haar-like feature approach for night-time gray-level video sequences. The approach detects vehicles at nighttime by searching for headlights (i.e. the RoI) in recorded frames. Experiments demonstrate the effectiveness of the proposed system. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test11() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Expression-Based Automated Deception Detection with Computer Vision Techniques");
		String[] author = {"Andrew John Wilson"};
		tester.setAuthors(author);
		tester.setSupervisors("Patrice Delmas, Georgy Gimel’farb and Burkhard Wuensche");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("This thesis describes an investigation into the utility of facial expression information for the task of automated deception detection, using methods from computer vision. We present a thorough review of the current understanding of deception in Psychology, and then document previous efforts within computer vision to address the problem. We develop a deceptive interview scenario based on the widely used mock crime paradigm, as a way to obtain data containing facial behaviour during deception. Subjects are recorded using the Microsoft Kinect and Fujifilm W3 cameras, with depth maps obtained from each. All image sequences and corresponding depth maps are registered with respect to face location. For this, we obtain an affine transformation for each frame, derived from fiducial face feature points tracked via template matching. From each registered frame, and optionally for each depth frame, a feature vector is constructed, containing a number of concatenated histograms of oriented spatio-temporal gradients. Each component histogram represents an isolated sub-region of the face that provides strong expression information. This formulation allows the feature vector to account for both structural and dynamic information in the image sequence. Following this, we develop a deception detection scheme based on dimensionality reduction through principal component analysis, followed by classification via the nearest-neighbour algorithm. The accuracy of the system at detecting deception is evaluated by leave-onesubject-out cross-validation. The results of the validation testing did not support the efficacy of our algorithm on the dataset, nor did they support the inclusion of the depth channel as a way to increase accuracy. Finally, we identify some shortcomings of the work, providing suggestions and directions for future research in the area. ");
		fail("Not yet implemented");
	}
	
	@Test
	public void test12() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("An Investigation of Steganalysis Techniques");
		String[] author = {"Tu Qian"};
		tester.setAuthors(author);
		tester.setSupervisors("Dr S Manoharan");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Steganography is the art of confidential communication. Steganography has a long history, which can be traced back to 440BC, and it is currently being exploited by digital technology in the modern world. The digital carrier that it uses can be of several different types, such as digital text, disk space, network packets, software, digital audio and digital images. Steganalysis, the countermeasure to steganography, is designed to detect and analyse the hidden data disseminated throughout a medium by steganography. This thesis focuses on investigating steganalysis techniques. To this end, it presents a review of both steganography and steganalysis before analysing and comparing three different steganalysis techniques. The steganalysis techniques we have investigated are the Histogram Characteristic Function (HCF) Technique, the Regular-Singular Analysis (RS) Technique and Raw Quick Pair (RQP) Technique. This thesis presents a comprehensive summary of the performance, characteristics and limitations of these three techniques.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test13() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("TCP Timing Quality Measurements for VoIP Applications and Services");
		String[] author = {"Firas Ghazzi"};
		tester.setAuthors(author);
		tester.setSupervisors("Ulrich Speidel");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("In a world of ever changing Internet infrastructure and an increasing usage of Voice over IP (VoIP) services, measurements must be made to determine whether VoIP service quality is improving or degrading. With the Internet being comprised of many small, modular networks, changes to infrastructure happen in isolation leading to potential unintended consequences. The main objective of this thesis is to present analysis tools for measuring VoIP service quality atop the Transmission Control Protocol (TCP). Using data collected by the International Internet Beacon Experiment (IIBEX) we produce long term data, providing insights into VoIP service quality for various countries and links.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test14() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Improving Statistical Anomaly Exploitation in Texas Hold’em Poker");
		String[] author = {"Kevin Norris"};
		tester.setAuthors(author);
		tester.setSupervisors("Ian Watson");
		tester.setDegree("Master of Science");
		tester.setDegreeDiscp("Computer Science");
		tester.setAbstractx("This thesis advances statistical exploitation in Texas Hold’em poker, showcasing the viability of this method for creating a world class poker playing agent. A world class poker player must be both resilient to exploitation and able to exploit opponents since skill in poker is determined by a player’s win rate over a range of opponents of varying skill levels. The resilience to exploitation ensures the player does not lose to any of the opponents to a great degree, and the exploitation capabilities ensure the player is able to achieve a high win rate against some, enabling a high sum total win rate over the entire range of opponents. The resilience to exploitation is provided by an approximate Nash equilibrium base strategy that the agent plays in every game state where it is uncertain. The exploitation is provided by the statistical exploitation module that models the opponent and provides exploitive actions in game states where it is certain the opponent is exploitable. Our evaluations show that the strategy resulting from the combination of an approximate Nash equilibrium strategy and the improved statistical exploitation module is as resilient to exploitation as the approximate Nash equilibrium strategy on its own, even against dynamic and strong opponents, and is capable of exploiting some opponents. This is made possible by the statistical exploitation modules safe exploitation, which does not increase the exploitability of the resulting strategy. This leads us to conclude that this method of creating poker playing agents is a viable method of creating a world class poker player.");
		fail("Not yet implemented");
	}
	
	@Test
	public void test15() {
		MetadataStorer tester = new MetadataStorer();
		tester.setTitle("Change Detection in Pattern Stream Mining");
		String[] author = {"Minmin Zhang"};
		tester.setAuthors(author);
		tester.setSupervisors("Professor Dr. Gillian Dobbie and Dr. Yun Sing Koh");
		tester.setDegree("Master in Science");
		tester.setDegreeDiscp(null);
		tester.setAbstractx("Data stream mining is becoming very important in many application areas, such as the stock market, network traffic, web logs and ATM transactions. A data stream consists of an ordered sequence of instances and because there are usually a large number of instances along with limited computing and storage capabilities, algorithms that read the data only once are preferred. There has been some research that focuses on finding when a concept has changed, given some knowledge about the previous instances in the data stream, but little on determining the characteristics of that change. In this thesis we concentrate on finding the characteristics of the changes that occur, using frequent itemset mining techniques. We propose two approaches, both combining heuristic and statistical approaches to analyse changes that have occurred within a stream at itemset level. Hoeffding Bound and Bernstein Bound Inequality are two statistical methods used in our research. In order to handle the infinite length and limited labelled data streams, our approaches incorporate a sliding window algorithm and a couple of effective data structure such as CP tree, Can tree and SPO tree to identify three types of change: extension, reduction, and support fluctuation. To experiment and evaluate our algorithms, we customized the IBM Quest Synthetic Data Generator to create types of dataset containing those three types of change. Our algorithms have been fully tested on both synthetic and real world datasets. ");
		fail("Not yet implemented");
	}
}
