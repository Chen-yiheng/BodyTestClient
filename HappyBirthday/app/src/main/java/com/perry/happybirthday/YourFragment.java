package com.perry.happybirthday;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by perry on 2018/2/1.
 */

public class YourFragment extends Fragment {
    private ImageView noteImageView;
    private TextView yourWordView;
    private TextView myWordView;
    private CardView cardView;
    private List<Note> noteList;
    private int listIndex;
    private GestureDetector detector;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_your, container, false);
        noteImageView = view.findViewById(R.id.imageView);
        yourWordView = view.findViewById(R.id.note);
        myWordView = view.findViewById(R.id.myword);
        cardView = view.findViewById(R.id.cardView);
        detector = new GestureDetector(getActivity(), new GestureDetector
                .SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                if (e1.getRawX() - e2.getRawX() > 200) {
                    if (listIndex == (noteList.size() - 1)) {
                        Toast.makeText(getActivity(), "已经是最后一页", Toast.LENGTH_SHORT)
                                .show();
                        return true;
                    }
                    initView(++listIndex);
                    return true;
                }
                if (e2.getRawX() - e1.getRawX() > 200) {
                    if (listIndex == 0) {
                        Toast.makeText(getActivity(), "已经是第一页", Toast.LENGTH_SHORT)
                                .show();
                        return true;
                    }
                    initView(--listIndex);
                    return true;
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
        initNoteList();
        initView(0);
        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return true;
            }
        });
        return view;
    }


    private void initNoteList() {
        noteList = new ArrayList();
        Note note1 = new Note(R.drawable.bir,
                "吃货，生日快乐！！\n在这里，你将会回忆起上一年的酸甜苦辣\n" +
                        "在这里，你将会想起某些人某些事\n" +
                        "在这里，你可能会再次去做一次同样的事\n" +
                        "请向左拨开始", "评语：");
        noteList.add(note1);
        Note note2 = new Note(R.drawable.b1, "2017年1月18日23:29\n" +
                "第一次在没有家人的陪伴中过生日，还好今天一扫往日的阴雨绵绵\n" +
                "阳光明媚\n" +
                "谢谢大外甥的“榨菜”和青宝贝的“鸡蛋”", "评语：二月生日的我每次都是这样过的，" +
                "你这样炫是在拉仇恨吧");
        noteList.add(note2);
        Note note3 = new Note(R.drawable.b2, "2017年1月24日22:04 \n带着我帽子的小短腿好萌萌哒",
                "评语：我也想要一个这样的弟弟....");
        noteList.add(note3);

        Note note5 = new Note(R.drawable.b3, "2017年2月4日21:27\n" +
                "花费一个下午和姐姐制作蛋糕饼干\n" + "我是专业打下手30年\n" +
                "姐姐制作的奥尔良烤翅还不赖\n" +
                "只怪人数有点多", "评语：还真挺不赖，但打下手的人只有1%的功劳，" +
                "而且你还把吃货的本性暴露出来");
        noteList.add(note5);
        Note note6 = new Note(R.drawable.b4, "2017年2月13日19:40\n" +
                "两个路痴骑着小电动说好去南国花卉科技园赏花，结果骑到大半路就云里雾里，" +
                "百度地图都拯救不了我们，还绕了大半圈，最终还是原路返回，" +
                "被倩给蠢笑了大半天严重怀疑百度地图只对那些大城市才能用", "评语：我也是路痴，" +
                "不过严重程度应该比你低，还有竟然翻到了组长的照片");
        noteList.add(note6);

        Note note7 = new Note(R.drawable.b6, "2017年3月7日 13:30\n" +
                "151的男神们超有爱超帅\n" +
                "惊喜不断（神秘礼物的惊吓与欢乐不断）\n" +
                "礼物包装盒我的书包很配哦", "评语：你们班人数真多，" +
                "我们只有17个人");
        noteList.add(note7);

        Note note4 = new Note(R.drawable.b5, "2017年4月4日 12:38\n" +
                "戴我帽子的说烧猪与啤酒更配哦\n" +
                "你觉得咧", "评语：不觉得，" +
                "你吃什么都香");
        noteList.add(note4);

        Note note8 = new Note(R.drawable.b7, "2017年4月26日 16；44\n" +
                "神经病！！！\n" +
                "我跟你有什么仇什么怨\n" +
                "你要把我的车藏起来\n" +
                "害我以为被偷了", "评语：少有的发怒说说，" +
                "看来把你气得不轻，月有阴晴圆缺！！！");
        noteList.add(note8);

        Note note10 = new Note(R.drawable.b9, "2017年5月8日 21:46\n" +
                "灵魂画手已上线", "评语：有点意思！！！" +
                "有点人样了，值得鼓励。");
        noteList.add(note10);
        Note note11 = new Note(R.drawable.b10, "2017年5月10日 10:00\n" +
                "竟然被人赤裸裸的嫌弃\n" +
                "我也不想的\n" +
                "可是没方法\n" +
                "我有一张吃货的嘴啊", "评语：这人谁啊！，" +
                "竟然比我还直接，佩服！");
        noteList.add(note11);
        Note note12 = new Note(R.drawable.b11, "2017年6月1日00:43\n" +
                "感觉自己无形之中得罪了很多人\n" +
                "有什么意见可以私聊我\n" +
                "别憋着难受\n" +
                "不好意思的话可以叫熟人帮帮忙告知我一声", "评语：何必这样，" +
                "做得再好也会有人不满，不需要改变自己取悦别人。");
        noteList.add(note12);
        Note note13 = new Note(R.drawable.b12, "2017年7月22日 00:17\n" +
                "雅晴小美女生日粗卡\n" +
                "虽然已过\n" +
                "就算是迟来的祝福咯\n" +
                "昨晚你最美", "评语：漂亮啊！");
        noteList.add(note13);
        Note note14 = new Note(R.drawable.b13, "2017年8月17日22:40\n" +
                "第一波爱\n" +
                "再一次相聚于小树家\n" +
                "附上三位大厨的美照\n" +
                "谢谢款待 满足", "评语：对这个我只能说：" +
                "666");
        noteList.add(note14);
        Note note15 = new Note(R.drawable.b14, "2017年9月2日 23:32\n" +
                "喜迎十九大 青春建新功\n" +
                "未来的路我们一起奋斗一起走\n" +
                "热烈祝贺流芳文化团临时党支部成立", "评语：不错不错，" +
                "有点政治意识");
        noteList.add(note15);
        Note note16 = new Note(R.drawable.b15, "2017年10月14日 17:57\n" +
                "给大家介绍一下：\n" +
                "苏峰琅，中长头发，喜欢美女帅哥，喜欢吃，那些都不是重点，重点是她是一个女生",
                "评语：前面几句略过，" +
                        "直接说后面");
        noteList.add(note16);
        Note note17 = new Note(R.drawable.b16, "2017年11月18日 10:38\n" +
                "半途下车\n" +
                "遇一朵开得正艳的花", "评语：生命的气息。");
        noteList.add(note17);
        Note note18 = new Note(R.drawable.b17, "2017年12月14日16:23\n" +
                "身心疲惫", "评语：累了就歇歇");
        noteList.add(note18);
        Note note19 = new Note(R.drawable.b18, "2018年1月3日 16：01\n" +
                "感觉自己得到了一个假的范围 背了20多页的内容考的没有几道",
                "评语：就是这张图");
        noteList.add(note19);
        Note note20 = new Note(R.drawable.b19, "2018年1月15日21:03\n" +
                "三个精致的叛逆的“猪猪人”出游记\n" +
                "伪湛江的我在她们的带领下尽情的逛逛逛\n" +
                "老是被嫌弃的拍照技术我也是没方法啊\n" +
                "因为一般我都是喜欢叫别人帮我拍照的\n", "评语：走走挺好的");
        noteList.add(note20);
        Note note21 = new Note(R.drawable.end, "", "评语：看完了吧！是不是想起了什么？" +
                "还满意吧！唉，不满意，我也没方法了，时间仓促，网络又差，只能做到这一步了");
        noteList.add(note21);

    }

    private void initView(int index) {
        Note note = noteList.get(index);
        Glide.with(getActivity()).load(note.getImageId()).into(noteImageView);
        myWordView.setText(note.getMyWord());
        yourWordView.setText(note.getYourWord());

    }
    
    

}
