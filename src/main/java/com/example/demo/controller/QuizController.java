package com.example.demo.controller;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.QuestionRequest;
import com.example.demo.dto.QuestionResponse;
import com.example.demo.dto.Quiz;
import com.example.demo.global.api.Result;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/quiz")
@RestController
public class QuizController {

    private final OpenAiChatClient chatClient;

    @GetMapping
    public Result<Quiz> askQuiz(@RequestParam String category) {
        BeanOutputParser<Quiz> parser = new BeanOutputParser<>(Quiz.class);

        String promptString = """
            {category}에 관련된 개발자 면접 질문을 한국어로 만들어줘.
            {format}
            """;

        PromptTemplate template = new PromptTemplate(promptString);
        template.add("category", category);
        template.add("format", parser.getFormat());
        template.setOutputParser(parser);

        Prompt prompt = template.create();
        ChatResponse response = chatClient.call(prompt);
        String text = response.getResult().getOutput().getContent();

        return new Result<>(HttpStatus.OK, "퀴즈 요청 성공", parser.parse(text));
    }

    @PostMapping
    public Result<QuestionResponse> askQuestionFeedback(@RequestBody QuestionRequest questionRequest) {
        BeanOutputParser<QuestionResponse> parser = new BeanOutputParser<>(QuestionResponse.class);

        String question = questionRequest.getQuestion();
        String answer = questionRequest.getAnswer();
        String promptString = """
                퀴즈: {question}와 답변: {answer}을 보고 피드백과 모범 답변을 단계적으로 잘 작성해주세요.
                또한 답변이 100점 만점에 몇 점인지 알려줘.
                {format}

                [피드백 및 모범 답변 작성 가이드라인]

                1. 문제에 대한 피드백: 문제의 명확성, 난이도, 충분한 정보 제공 여부 등을 고려하여 피드백을 작성해주세요.

                2. 모범 답변 작성:
                   - 정확한 답변 제시: 퀴즈에 대한 정확한 답변을 제시해주세요.
                   - 설명과 이유 제공: 답변의 이유나 과정을 설명하여 왜 해당 답변이 올바른지 설명해주세요.
                   - 추가 정보 제공: 퀴즈와 관련된 추가 정보나 관련 용어, 개념을 제공하여 이해를 돕는데 도움이 되는 정보를 포함해주세요.
                   - 명확하고 간결하게 작성: 모범 답변을 명확하고 간결하게 작성하여 이해하기 쉽도록 해주세요.

                [포맷 가이드]
                - 피드백 및 모범 답변은 번호별로 작성해주세요.
                - 각 항목은 문장으로 시작하고 마칩니다.
                - 필요 시 추가 정보나 예시를 포함해주세요.
                - 점수를 아주 냉철하게 매겨줘
            """;

        PromptTemplate template = new PromptTemplate(promptString);
        template.add("format", parser.getFormat());
        template.add("question", question);
        template.add("answer", answer);
        template.setOutputParser(parser);

        Prompt prompt = template.create();
        ChatResponse response = chatClient.call(prompt);
        String text = response.getResult().getOutput().getContent();

        return new Result<>(HttpStatus.OK, "퀴즈 피드백 요청 성공", parser.parse(text));
    }
}
