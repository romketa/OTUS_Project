package otus.ru.java.qa.professional.homework.steps.components;

import com.google.inject.Inject;

import io.cucumber.java.ru.����;
import io.cucumber.java.ru.�����;
import otus.ru.java.qa.professional.homework.components.ContainerLessonsBlock;
import otus.ru.java.qa.professional.homework.steps.World;

public class ContainerLessonsBlockSteps {

    private World world;

    @Inject
    private ContainerLessonsBlockSteps(World world) {
        this.world = world;
    }

    @Inject
    private ContainerLessonsBlock containerLessonsBlock;

    @����("����� ���� {string} �� ������� ��������")
    public void findLessonOnMainPage(String courseName) {
        System.out.println("|-------|-------| �������� ����� ���������� �����: ");
        world.lesson = containerLessonsBlock.findLessonInBlock(courseName);
        System.out.println("|-------|-------| ���� ��� ������: " + world.lesson.getName());
    }

    @����("����� ���� �� ���� {string} ��� ����� ���� ����, ���� ����� �� ��������� ���� ���")
    public void findLessonByDate(String sDate){
        world.lesson = containerLessonsBlock.findLessonInBlockByLocalDate(sDate);
    }

    @�����("������� ��������� ���� �� �������")
    public void printOutLesson(){
        System.out.println("���� - " + world.lesson.getName() + " ���� ������ - " + world.lesson.getDate());
    }
}
